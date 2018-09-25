package com.inc.silence.weather.data

import com.inc.silence.weather.domain.entity.weather.CityInfo
import com.inc.silence.weather.domain.entity.weather.Forecast
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    companion object {
        private const val BASE = "data/2.5"
        private const val WEATHER = "$BASE/weather"
        private const val FORECAST = "$BASE/forecast"
    }

    @GET(WEATHER) fun wheather(@Query("lat") lat: Double,
                               @Query("lon") lon: Double): Deferred<CityInfo>

    @GET(FORECAST) fun forecast(@Query("lat") lat: Double,
                                 @Query("lon") lon: Double): Deferred<Forecast>
}