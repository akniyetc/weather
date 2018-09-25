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
import com.inc.silence.weather.domain.entity.weather.Forecast
import com.inc.silence.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.experimental.Deferred

class WeatherRepositoryImpl(private val networkHandler: NetworkHandler,
                            private val service: WeatherService) : WeatherRepository {

    override suspend fun weather(lat: Double, lon: Double) =
            request(service.wheather(lat, lon), { it.toWeather() }, CityInfo.empty())

    override suspend fun forecast(lat: Double, lon: Double) =
            request(service.forecast(lat, lon), { it.list }, Forecast.empty())

    private suspend fun <T, R> request(call: Deferred<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return when (networkHandler.isConnected) {
            true -> try {
                Right(transform((call.await() ?: default)))
            } catch (t: Throwable) {
                Left(ServerError())
            }
            false, null -> Left(NetworkConnection())
        }
    }
}