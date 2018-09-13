package com.inc.silence.weather.data.repository

import android.arch.lifecycle.LiveData
import android.util.Log
import com.inc.silence.weather.data.exception.Failure
import com.inc.silence.weather.data.func.Either
import com.inc.silence.weather.domain.entity.location.LatLon
import com.inc.silence.weather.domain.repository.LocationRepository
import com.inc.silence.weather.extension.toLatLon
import com.inc.silence.weather.ui.base.LocationLiveData
import com.inc.silence.weather.ui.base.WeatherFailure.LocationFailure

class LocationRepositoryImpl(private val locationLiveData: LocationLiveData) : LocationRepository {

    override fun location(): Either<Failure, LatLon> {
        Log.d("Location", "${locationLiveData.value?.latitude} - ${locationLiveData.value?.longitude}")
        return request(locationLiveData, { it.toLatLon() })
    }

    private fun <T, R> request(liveData: LiveData<T>, transform: (T) -> R): Either<Failure, R> {
        return try {
            Either.Right(transform(liveData.value!!))
        } catch (exception: Throwable) {
            Either.Left(LocationFailure())
        }
    }
}