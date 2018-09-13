package com.inc.silence.weather.domain.interactor

import com.inc.silence.weather.domain.entity.location.LatLon
import com.inc.silence.weather.domain.repository.WeatherRepository
import com.inc.silence.weather.domain.entity.weather.WeatherDetails
import com.inc.silence.weather.domain.interactor.GetWeather.Params
import com.inc.silence.weather.domain.interactor.base.UseCase

class GetWeather(private val weatherRepository: WeatherRepository) : UseCase<WeatherDetails, Params>() {

    override suspend fun run(params: Params) =
            weatherRepository.weather(params.latLon.lat, params.latLon.lon)

    data class Params(val latLon: LatLon)
}