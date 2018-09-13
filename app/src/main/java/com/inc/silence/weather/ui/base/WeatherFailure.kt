package com.inc.silence.weather.ui.base

import com.inc.silence.weather.data.exception.Failure

class WeatherFailure {
    class NonExistentWeather: Failure.FeatureFailure()
    class LocationFailure: Failure.FeatureFailure()
}