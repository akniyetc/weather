package com.inc.silence.weather.presentation

import android.arch.lifecycle.MutableLiveData
import com.inc.silence.weather.domain.entity.location.LatLon
import com.inc.silence.weather.domain.entity.weather.WeatherDetails
import com.inc.silence.weather.domain.interactor.GetLocation
import com.inc.silence.weather.domain.interactor.GetWeather
import com.inc.silence.weather.domain.interactor.GetWeather.Params
import com.inc.silence.weather.domain.interactor.base.UseCase.None
import com.inc.silence.weather.presentation.base.BaseViewModel

class WeatherViewModel(private val getWeather: GetWeather,
                       private val getLocation: GetLocation) : BaseViewModel() {

    var weatherDetails: MutableLiveData<WeatherView> = MutableLiveData()
    var location: MutableLiveData<LatLon> = MutableLiveData()

    fun loadWeatherDetails() {
        getLocation(None()){
            it.either(::handleFailure, ::handleLocation)
        }
    }

    private fun handleLocation(latLon: LatLon) {
        getWeather(Params(latLon))
        {
            it.either(::handleFailure, ::handleWeatherDetails)
        }
    }

    private fun handleWeatherDetails(weather: WeatherDetails) {
        this.weatherDetails.value = WeatherView(weather.id, weather.name, weather.weather, weather.main)
    }

}