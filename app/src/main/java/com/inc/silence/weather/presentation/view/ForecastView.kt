package com.inc.silence.weather.presentation.view

import com.inc.silence.weather.domain.entity.weather.WeatherInfo

data class ForecastView(
        val date: String,
        val temperature: Double,
        val weather: WeatherInfo
)