package com.inc.silence.weather.presentation

import com.inc.silence.weather.domain.entity.Main
import com.inc.silence.weather.domain.entity.WeatherInfo

data class WeatherView (
        val id : Long,
        val name: String,
        val weather: List<WeatherInfo>,
        val main: Main
)