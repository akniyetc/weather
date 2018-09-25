package com.inc.silence.weather.presentation

import android.arch.lifecycle.MutableLiveData
import com.inc.silence.weather.domain.entity.weather.CityInfo
import com.inc.silence.weather.domain.entity.weather.WeatherDetails
import com.inc.silence.weather.domain.interactor.GetWeather
import com.inc.silence.weather.presentation.base.BaseViewModel
import com.inc.silence.weather.presentation.view.ForecastView
import com.inc.silence.weather.presentation.view.WeatherView

class WeatherViewModel(private val getWeather: GetWeather) : BaseViewModel() {

    var weatherDetails: MutableLiveData<WeatherView> = MutableLiveData()

    fun getWeather() = loadData({ getWeather.getWeather() }) {
        it.either(::handleFailure, ::handleWeatherDetails)
    }

    private fun handleWeatherDetails(weather: WeatherDetails) {
        this.weatherDetails.value = WeatherView(weather.id, weather.name, weather.weather, weather.main)
    }


    var forecasts: MutableLiveData<List<ForecastView>> = MutableLiveData()

    fun getForecast() = loadData({ getWeather.getForecast() }) {
        it.either(::handleFailure, ::handleForecasts)
    }

    private fun handleForecasts(forecasts: List<CityInfo>) {
        val forecastsView = forecasts.map { ForecastView(it.dtTxt, it.main.temp, it.weather[0]) }
        this.forecasts.value = forecastsView
    }
}