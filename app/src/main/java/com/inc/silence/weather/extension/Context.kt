package com.inc.silence.weather.extension

import android.content.Context
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.inc.silence.weather.domain.entity.location.LatLon

val Context.networkInfo: NetworkInfo? get() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

fun Location.toLatLon(): LatLon {
    return LatLon(latitude, longitude)
}