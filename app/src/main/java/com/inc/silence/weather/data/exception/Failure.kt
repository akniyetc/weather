package com.inc.silence.weather.data.exception

sealed class Failure {
    class NetworkConnection : Failure()
    class ServerError : Failure()

    abstract class FeatureFailure : Failure()
}