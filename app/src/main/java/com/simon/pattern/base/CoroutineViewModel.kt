package com.simon.pattern.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class CoroutineViewModel : ViewModel() {

    protected val viewModelScope = ViewModelCoroutineScope() // change to 'viewModelScope' after release

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

class ViewModelCoroutineScope(
    job: Job = SupervisorJob(),
    dispatcher: CoroutineDispatcher = Dispatchers.IO // Default IO assuming we almost always call network for data
) : CoroutineScope {
    override val coroutineContext: CoroutineContext = job + dispatcher
}