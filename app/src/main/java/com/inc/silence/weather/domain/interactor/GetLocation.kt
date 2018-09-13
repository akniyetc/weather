package com.inc.silence.weather.domain.interactor

import com.inc.silence.weather.domain.entity.location.LatLon
import com.inc.silence.weather.domain.interactor.base.UseCase
import com.inc.silence.weather.domain.interactor.base.UseCase.None
import com.inc.silence.weather.domain.repository.LocationRepository

class GetLocation(private val locationRepository: LocationRepository) : UseCase<LatLon, None>() {
    override suspend fun run(params: None) = locationRepository.location()
}