package com.inc.silence.weather.data

import com.inc.silence.weather.domain.entity.weather.CityInfo
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    companion object {
        private const val WEATHER = "data/2.5/weather"
    }

    @GET(WEATHER) fun wheather(@Query("lat") lat: Double,
                               @Query("lon") lon: Double): Deferred<CityInfo>
}