package com.inc.silence.weather.domain.entity.location

data class LatLon(val lat: Double, val lon: Double) {
    companion object {
        fun empty() = LatLon(0.0, 0.0)
    }
}