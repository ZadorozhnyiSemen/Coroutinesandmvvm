package com.simon.pattern.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class CoroutineViewModel : ViewModel() {

    private val viewModelScope = MainScope()

    fun launchLite(task: suspend () -> Unit): Job {
        return viewModelScope.launch(Dispatchers.Default) { task() }
    }

    fun launchMain(task: suspend () -> Unit): Job {
        return viewModelScope.launch(Dispatchers.Main) { task() }
    }

    fun launchIO(task: suspend () -> Unit): Job {
        return viewModelScope.launch(Dispatchers.IO) { task() }
    }

    fun <T> asyncLite(task: suspend () -> T): Deferred<T> {
        return viewModelScope.async(Dispatchers.Default) { task() }
    }

    fun <T> asyncMain(task: suspend () -> T): Deferred<T> {
        return viewModelScope.async(Dispatchers.Main) { task() }
    }

    fun <T> asyncIO(task: suspend () -> T): Deferred<T> {
        return viewModelScope.async(Dispatchers.IO) { task() }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}
