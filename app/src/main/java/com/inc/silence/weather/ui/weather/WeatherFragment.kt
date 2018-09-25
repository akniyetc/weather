package com.inc.silence.weather.ui.weather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.inc.silence.weather.R
import com.inc.silence.weather.data.exception.Failure
import com.inc.silence.weather.data.exception.Failure.NetworkConnection
import com.inc.silence.weather.data.exception.Failure.ServerError
import com.inc.silence.weather.extension.*
import com.inc.silence.weather.presentation.WeatherViewModel
import com.inc.silence.weather.presentation.view.ForecastView
import com.inc.silence.weather.presentation.view.WeatherView
import com.inc.silence.weather.ui.base.BaseFragment
import com.inc.silence.weather.ui.base.WeatherFailure.NonExistentWeather
import com.inc.silence.weather.ui.forecast.ForecastAdapter
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.android.viewmodel.ext.android.viewModel


class WeatherFragment : BaseFragment() {

    override fun layoutId() = R.layout.fragment_weather

    private val weatherViewModel: WeatherViewModel by viewModel()
    private val forecastAdapter: ForecastAdapter = ForecastAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        weatherViewModel.apply {
            observe(weatherDetails, ::renderWeather)
            observe(forecasts, ::renderForecasts)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()
        loadWeatherInfo()
    }

    private fun loadWeatherInfo() {
        showProgress()
        checkLocationPermission()
    }

    private fun renderWeather(detailView: WeatherView?) {
        detailView?.apply {
            tv_city_name.text = name
            tv_weather_name.text = weather[0].description
            tv_degree.text = main.temp.toDegree()
        }
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> {notify(R.string.failure_network_connection); hideProgress()}
            is ServerError -> {
                notifyWithAction(R.string.failure_server_error, R.string.failure_try_again_snack_bar, ::loadWeatherInfo)
                hideProgress()
            }
            is NonExistentWeather -> { notify(R.string.failure_weather_non_existent); hideProgress() }
        }
    }

    private fun initializeView() {
        rvForecasts.layoutManager = LinearLayoutManager(context)
        rvForecasts.adapter = forecastAdapter
        rvForecasts.dispatchNestedScrolling()
    }

    private fun renderForecasts(forecasts: List<ForecastView>?) {
        forecasts?.apply {
            forecastAdapter.collection = this.sortByDate()
        }
    }

    private fun checkLocationPermission() {
        if(ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(listOf(Manifest.permission.ACCESS_FINE_LOCATION).toTypedArray(), PERMISSIONS_REQUEST_LOCATION)
        else {
            weatherViewModel.getWeather()
            weatherViewModel.getForecast()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST_LOCATION -> {
                weatherViewModel.getWeather()
                weatherViewModel.getForecast()
            }
            else -> notify(R.string.permission_denied)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        const val PERMISSIONS_REQUEST_LOCATION = 1
    }
}