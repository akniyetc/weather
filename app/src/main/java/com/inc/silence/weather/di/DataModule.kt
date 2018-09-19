package com.inc.silence.weather.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.inc.silence.weather.data.func.NetworkHandler
import com.inc.silence.weather.data.repository.LocationRepositoryImpl
import com.inc.silence.weather.data.repository.WeatherRepositoryImpl
import com.inc.silence.weather.domain.interactor.GetWeather
import com.inc.silence.weather.domain.repository.LocationRepository
import com.inc.silence.weather.domain.repository.WeatherRepository
import com.inc.silence.weather.presentation.WeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val dataModule = module {
    single { NetworkHandler(androidContext()) }
    single { WeatherRepositoryImpl(get(), get()) as WeatherRepository }
    single { GetWeather(get(), get()) }
    single { createFusedClient(androidContext()) }
    viewModel { WeatherViewModel(get()) }
    single { LocationRepositoryImpl(get()) as LocationRepository }
}

fun createFusedClient(context: Context) : FusedLocationProviderClient {
    return LocationServices.getFusedLocationProviderClient(context)
}