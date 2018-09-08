package com.inc.silence.weather.domain.entity

import com.inc.silence.weather.extension.empty

data class CityInfo(
        val coord: Coordinate,
        val weather: Weather,
        val base: String,
        val main: Main,
        val id: Long,
        val name: String,
        val cod: Long) {

    companion object {
        fun empty() = CityInfo(
                Coordinate(0F, 0F),
                Weather(emptyList()),
                String.empty(),
                Main(0.0, 0, 0, 0.0, 0.0),
                0L,
                String.empty(),
                0L
        )
    }

    fun toWeather() = WeatherDetails(id, name, weather, main)
}

data class Coordinate(val lon: Float, val lat: Float)

data class Weather(val infos: List<WeatherInfo>)

data class WeatherInfo(
        val id: Long,
        val main: String,
        val description: String,
        val icon: String
)

data class Main(
        val temp: Double,
        val pressure: Int,
        val humidity: Int,
        val tempMin: Double,
        val tempMax: Double
)
