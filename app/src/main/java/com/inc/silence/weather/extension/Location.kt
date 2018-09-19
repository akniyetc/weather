package com.inc.silence.weather.extension

import android.location.Location
import com.inc.silence.weather.domain.entity.location.LatLon

fun Location.toLatLon(): LatLon {
    return LatLon(latitude, longitude)
}