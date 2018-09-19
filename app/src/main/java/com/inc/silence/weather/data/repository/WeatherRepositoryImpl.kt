package com.inc.silence.weather.data.repository

import com.inc.silence.weather.data.WeatherService
import com.inc.silence.weather.data.exception.Failure
import com.inc.silence.weather.data.exception.Failure.NetworkConnection
import com.inc.silence.weather.data.exception.Failure.ServerError
import com.inc.silence.weather.data.func.Either
import com.inc.silence.weather.data.func.Either.Left
import com.inc.silence.weather.data.func.Either.Right
import com.inc.silence.weather.data.func.NetworkHandler
import com.inc.silence.weather.domain.entity.weather.CityInfo
import com.inc.silence.weather.domain.entity.weather.WeatherDetails
import com.inc.silence.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.experimental.Deferred

class WeatherRepositoryImpl (private val networkHandler: NetworkHandler,
                             private val service: WeatherService) : WeatherRepository {

    override suspend fun weather(lat: Double, lon: Double): Either<Failure, WeatherDetails> {
        return when (networkHandler.isConnected) {
            true -> request(service.wheather(lat, lon), { it.toWeather()}, CityInfo.empty())
            false, null -> Left(NetworkConnection())
        }
    }

    private suspend fun <T, R> request(call: Deferred<T>, transform: (T) -> R, default: T) : Either<Failure, R> {
        return try {
            Right(transform((call.await() ?: default)))
        } catch (exception: Throwable) {
            Left(ServerError())
        }
    }
}