package com.inc.silence.weather.domain.entity.weather

import com.inc.silence.weather.extension.empty

data class WeatherDetails (
        val id : Long,
        val name: String,
        val weather: List<WeatherInfo>,
        val main: Main) {

    companion object {
        fun empty() = WeatherDetails(
                0L,
                String.empty(),
                emptyList(),
                Main(0.0, 0, 0, 0.0, 0.0))
    }
}