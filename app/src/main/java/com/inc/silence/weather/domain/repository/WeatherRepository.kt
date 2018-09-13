package com.inc.silence.weather.domain.repository

import com.inc.silence.weather.data.exception.Failure
import com.inc.silence.weather.data.func.Either
import com.inc.silence.weather.domain.entity.weather.WeatherDetails

interface WeatherRepository {
    fun weather(lat: Double, lon: Double) : Either<Failure, WeatherDetails>
}