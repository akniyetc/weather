package com.inc.silence.weather.domain.interactor

import com.inc.silence.weather.data.exception.Failure
import com.inc.silence.weather.data.func.Either
import com.inc.silence.weather.domain.entity.weather.CityInfo
import com.inc.silence.weather.domain.entity.weather.WeatherDetails
import com.inc.silence.weather.domain.repository.LocationRepository
import com.inc.silence.weather.domain.repository.WeatherRepository

class GetWeather(private val weatherRepository: WeatherRepository,
                 private val locationRepository: LocationRepository) {

    suspend fun getWeather() : Either<Failure, WeatherDetails> {
        val latLon = locationRepository.location()
        return weatherRepository.weather(latLon.lat, latLon.lon)
    }

    suspend fun getForecast() : Either<Failure, List<CityInfo>> {
        val latLon = locationRepository.location()
        return weatherRepository.forecast(latLon.lat, latLon.lon)
    }
}