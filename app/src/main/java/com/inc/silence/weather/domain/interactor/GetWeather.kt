package com.inc.silence.weather.domain.interactor

import com.inc.silence.weather.data.repository.WeatherRepository
import com.inc.silence.weather.domain.entity.WeatherDetails
import com.inc.silence.weather.domain.interactor.GetWeather.Params
import com.inc.silence.weather.domain.interactor.base.UseCase

class GetWeather(private val weatherRepository: WeatherRepository) : UseCase<WeatherDetails, Params>() {

    override suspend fun run(params: Params) =
            weatherRepository.weatherByCity(params.city)

    data class Params(val city: String)
}