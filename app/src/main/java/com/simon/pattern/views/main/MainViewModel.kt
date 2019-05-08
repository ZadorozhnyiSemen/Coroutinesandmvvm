package com.simon.pattern.views.main

import androidx.lifecycle.MutableLiveData
import com.simon.pattern.base.CoroutineViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainViewModel @Inject constructor() : CoroutineViewModel() {

    val textWithStatus = MutableLiveData<String>()

    fun checkIfCoroutinesWorking() {
        launchIO {
            delayTextUpdate()
        }
    }

    private suspend fun delayTextUpdate() {
        delay(10_000)
        textWithStatus.postValue("Background work done")
    }
}