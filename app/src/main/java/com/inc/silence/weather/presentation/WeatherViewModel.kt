package com.inc.silence.weather.presentation

import android.arch.lifecycle.MutableLiveData
import com.inc.silence.weather.domain.entity.WeatherDetails
import com.inc.silence.weather.domain.interactor.GetWeather
import com.inc.silence.weather.domain.interactor.GetWeather.Params
import com.inc.silence.weather.presentation.base.BaseViewModel

class WeatherViewModel(private val getWeather: GetWeather) : BaseViewModel() {

    var weatherDetails: MutableLiveData<WeatherView> = MutableLiveData()

    fun loadWeatherDetails(city: String) = getWeather(Params(city)) {
        it.either(::handleFailure, ::handleWeatherDetails)
    }

    private fun handleWeatherDetails(weather: WeatherDetails) {
        this.weatherDetails.value = WeatherView(weather.id, weather.name, weather.weather, weather.main)
    }

}