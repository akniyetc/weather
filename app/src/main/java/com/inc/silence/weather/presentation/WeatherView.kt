package com.inc.silence.weather.presentation

import com.inc.silence.weather.domain.entity.Main
import com.inc.silence.weather.domain.entity.Weather

data class WeatherView (
        val id : Long,
        val name: String,
        val weather: Weather,
        val main: Main
)