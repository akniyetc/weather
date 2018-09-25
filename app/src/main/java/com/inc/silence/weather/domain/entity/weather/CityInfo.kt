package com.inc.silence.weather.domain.entity.weather

import com.inc.silence.weather.extension.empty

data class CityInfo(
        val coord: Coordinate,
        val weather: List<WeatherInfo>,
        val base: String,
        val main: Main,
        val id: Long,
        val name: String,
        val cod: Long,
        val dt: Long,
        val dtTxt: String) {

    companion object {
        fun empty() = CityInfo(
                Coordinate(0F, 0F),
                emptyList(),
                String.empty(),
                Main(0.0, 0.0, 0, 0.0, 0.0),
                0L,
                String.empty(),
                0L,
                0L,
                String.empty()
        )
    }

    fun toWeather() = WeatherDetails(id, name, weather, main)
}

data class Coordinate(val lon: Float, val lat: Float)

data class WeatherInfo(
        val id: Long,
        val main: String,
        val description: String,
        val icon: String
)

data class Main(
        val temp: Double,
        val pressure: Double,
        val humidity: Int,
        val tempMin: Double,
        val tempMax: Double
)
