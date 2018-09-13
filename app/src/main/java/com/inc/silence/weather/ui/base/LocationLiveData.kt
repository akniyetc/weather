package com.inc.silence.weather.ui.base

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Location
import com.google.android.gms.location.*


class LocationLiveData constructor(appContext: Context) : LiveData<Location>() {

    private val mFusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(appContext)
    private var mLocationRequest: LocationRequest? = null

    private var mLocationCallback: LocationCallback? = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            for (location in locationResult.locations) {
                if (location != null)
                    value = location
            }
        }
    }

    init {
        initialize()
    }

    @SuppressLint("MissingPermission")
    private fun initialize() {
        createLocationRequest()
        mFusedLocationClient.lastLocation.addOnCompleteListener { task ->
            if (task.isSuccessful && task.result != null) {
                value = task.result
            } else {
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null)
            }
        }
    }

    private fun createLocationRequest() {
        mLocationRequest = LocationRequest()

        mLocationRequest.apply {
            this?.interval = 1000
            this?.fastestInterval = 5000
            this?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (mLocationCallback != null)
            mFusedLocationClient.removeLocationUpdates(mLocationCallback)
    }
}