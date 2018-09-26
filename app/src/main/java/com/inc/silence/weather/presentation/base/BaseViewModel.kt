package com.inc.silence.weather.presentation.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.inc.silence.weather.data.exception.Failure
import com.inc.silence.weather.data.func.Either
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main

abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    protected fun <T> loadData(data: suspend () -> Either<Failure, T>, onResult: (Either<Failure, T>) -> Unit = {}) {
        val job = GlobalScope.async(Dispatchers.IO) { data.invoke() }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

    protected fun <T, R> concatSuspend(first: suspend () -> Either<Failure, T>,
                                       second: suspend () -> Either<Failure, R>,
                                       onResult: (Either<Failure, T>, Either<Failure, R>) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            val firstJob = GlobalScope.async(Dispatchers.IO) { first.invoke() }
            val secondJob = GlobalScope.async(Dispatchers.IO) { second.invoke() }
            onResult(firstJob.await(), secondJob.await())
        }
    }
}