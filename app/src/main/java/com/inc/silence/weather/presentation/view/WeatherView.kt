package com.inc.silence.weather.presentation.view

import com.inc.silence.weather.domain.entity.weather.Main
import com.inc.silence.weather.domain.entity.weather.WeatherInfo

data class WeatherView(
        val id: Long,
        val name: String,
        val weather: WeatherInfo,
        val main: Main
)