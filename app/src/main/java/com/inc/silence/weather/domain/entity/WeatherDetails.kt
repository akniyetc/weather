package com.inc.silence.weather.domain.entity

import com.inc.silence.weather.extension.empty

data class WeatherDetails (
        val id : Long,
        val name: String,
        val weather: Weather,
        val main: Main) {

    companion object {
        fun empty() = WeatherDetails(
                0L,
                String.empty(),
                Weather(emptyList()),
                Main(0.0,0, 0, 0.0,0.0))
    }
}