package com.inc.silence.weather.data

import com.inc.silence.weather.domain.entity.CityInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    companion object {
        private const val WEATHER = "data/2.5/weather"
    }

    @GET(WEATHER) fun wheatherByCityName(@Query("q") city: String): Call<CityInfo>
}