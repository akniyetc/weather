package com.inc.silence.weather.data.repository

import com.inc.silence.weather.data.WeatherService
import com.inc.silence.weather.data.exception.Failure
import com.inc.silence.weather.data.exception.Failure.ServerError
import com.inc.silence.weather.data.exception.Failure.NetworkConnection
import com.inc.silence.weather.data.func.Either
import com.inc.silence.weather.data.func.Either.Right
import com.inc.silence.weather.data.func.Either.Left
import com.inc.silence.weather.data.func.NetworkHandler
import com.inc.silence.weather.domain.entity.CityInfo
import com.inc.silence.weather.domain.entity.WeatherDetails
import com.inc.silence.weather.domain.repository.WeatherRepository
import retrofit2.Call

class WeatherRepositoryImpl (val networkHandler: NetworkHandler,
                             val service: WeatherService) : WeatherRepository {

    override fun weatherByCity(city: String): Either<Failure, WeatherDetails> {
        return when (networkHandler.isConnected) {
            true -> request(service.wheatherByCityName(city), { it.toWeather()}, CityInfo.empty())
            false, null -> Left(NetworkConnection())
        }
    }

    private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T) : Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Right(transform((response.body() ?: default)))
                false -> Left(ServerError())
            }
        } catch (exception: Throwable) {
            Left(ServerError())
        }
    }
}