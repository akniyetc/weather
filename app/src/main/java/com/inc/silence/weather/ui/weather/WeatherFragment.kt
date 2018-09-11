package com.inc.silence.weather.ui.weather

import android.os.Bundle
import android.view.View
import com.inc.silence.weather.R
import com.inc.silence.weather.data.exception.Failure
import com.inc.silence.weather.extension.close
import com.inc.silence.weather.extension.failure
import com.inc.silence.weather.extension.observe
import com.inc.silence.weather.presentation.WeatherView
import com.inc.silence.weather.presentation.WeatherViewModel
import com.inc.silence.weather.ui.base.BaseFragment
import com.inc.silence.weather.ui.base.WeatherFailure
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.android.viewmodel.ext.android.viewModel

class WeatherFragment : BaseFragment() {

    override fun layoutId() = R.layout.fragment_weather

    val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        weatherViewModel.let {
            observe(weatherViewModel.weatherDetails, ::renderWeather)
            failure(weatherViewModel.failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgress()
        weatherViewModel.loadWeatherDetails("Astana")
    }

    private fun renderWeather(detailView: WeatherView?) {
        //tv_city_name.text = detailView?.name
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> { notify(R.string.failure_network_connection); close() }
            is Failure.ServerError -> { notify(R.string.failure_server_error); close() }
            is WeatherFailure.NonExistentMovie -> { notify(R.string.failure_weather_non_existent); close() }
        }
    }
}