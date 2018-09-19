package com.inc.silence.weather.data.repository

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.inc.silence.weather.domain.entity.location.LatLon
import com.inc.silence.weather.domain.repository.LocationRepository
import com.inc.silence.weather.extension.toLatLon
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import kotlin.coroutines.experimental.suspendCoroutine

class LocationRepositoryImpl(private val locationService: FusedLocationProviderClient) : LocationRepository {

    @SuppressLint("MissingPermission")
    override suspend fun location() : LatLon = suspendCoroutine { continuation ->
        GlobalScope.launch {
            try {
                withContext(Dispatchers.Main) {
                    locationService.lastLocation.addOnSuccessListener { continuation.resume(it.toLatLon()) }
                }
            } catch (t : Throwable) {
                continuation.resumeWithException(t)
            }
        }
    }
}