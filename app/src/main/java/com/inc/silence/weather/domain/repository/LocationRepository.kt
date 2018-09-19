package com.inc.silence.weather.domain.repository

import com.inc.silence.weather.domain.entity.location.LatLon

interface LocationRepository {

    suspend fun location() : LatLon
}