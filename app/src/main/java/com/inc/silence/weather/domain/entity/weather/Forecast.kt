package com.inc.silence.weather.domain.entity.weather

data class Forecast(
        val message: Double,
        val cnt: Int,
        val list: List<CityInfo>) {

    companion object {
        fun empty() = Forecast(0.0, 0, emptyList())
    }
}