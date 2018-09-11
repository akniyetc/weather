package com.inc.silence.weather.domain.repository

import com.inc.silence.weather.data.exception.Failure
import com.inc.silence.weather.data.func.Either
import com.inc.silence.weather.domain.entity.WeatherDetails

interface WeatherRepository {
    fun weatherByCity(city: String) : Either<Failure, WeatherDetails>
}