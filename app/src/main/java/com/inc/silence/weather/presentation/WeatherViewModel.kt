package com.inc.silence.weather.presentation

import android.arch.lifecycle.MutableLiveData
import com.inc.silence.weather.data.exception.Failure
import com.inc.silence.weather.data.func.Either
import com.inc.silence.weather.domain.entity.weather.WeatherDetails
import com.inc.silence.weather.domain.interactor.GetWeather
import com.inc.silence.weather.presentation.base.BaseViewModel
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main

class WeatherViewModel(private val getWeather: GetWeather) : BaseViewModel() {

    var weatherDetails: MutableLiveData<WeatherView> = MutableLiveData()

    private fun loadWeatherDetails(onResult: (Either<Failure, WeatherDetails>) -> Unit = {}) {
        val job = GlobalScope.async(Dispatchers.IO) { getWeather.getWeather() }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

    fun getWeather() {
        loadWeatherDetails {
            it.either(::handleFailure, ::handleWeatherDetails)
        }
    }

    private fun handleWeatherDetails(weather: WeatherDetails) {
        this.weatherDetails.value = WeatherView(weather.id, weather.name, weather.weather, weather.main)
    }

}