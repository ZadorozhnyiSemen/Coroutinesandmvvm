package com.simon.pattern.views.main

import androidx.lifecycle.MutableLiveData
import com.simon.pattern.base.CoroutineViewModel
import com.simon.pattern.base.SingleEvent
import com.simon.pattern.repository.AuthData
import com.simon.pattern.repository.SpotifyRepository
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val spotifyRepository: SpotifyRepository
) : CoroutineViewModel() {

    init {
        Timber.i("View model created")
    }

    val textWithStatus = MutableLiveData<String>()
    val userLoginRequired = MutableLiveData<SingleEvent<AuthData>>()
    val spotifyServiceReady = MutableLiveData<Boolean>(false)

    fun checkUserTokenAvailable() {
        launchLite {
            if (spotifyRepository.authToken.isEmpty()) {
                println("========================= post true")
                userLoginRequired.postValue(SingleEvent(spotifyRepository.authData))
            }
        }
    }

    fun obtainAccessToken(accessToken: String) {
        launchLite {
            spotifyRepository.authToken = accessToken
            spotifyServiceReady.postValue(true)
        }
    }

    fun checkIfCoroutinesWorking() {
        launchLite {
            delayTextUpdate()
        }
    }

    private suspend fun delayTextUpdate() {
        val song = spotifyRepository.searchSong("Yeah Yeah Yeah!")
        if (song == null) {
            textWithStatus.postValue("Search for son returns null")
        } else {
            println(song)
        }
    }
}