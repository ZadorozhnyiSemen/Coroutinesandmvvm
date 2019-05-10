package com.simon.pattern.views.main

import androidx.lifecycle.MutableLiveData
import com.simon.pattern.base.CoroutineViewModel
import com.simon.pattern.repository.AuthData
import com.simon.pattern.repository.SpotifyRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val spotifyRepository: SpotifyRepository
) : CoroutineViewModel() {

    val textWithStatus = MutableLiveData<String>()
    val userLoginRequired = MutableLiveData<AuthData>()
    val spotifyServiceReady = MutableLiveData<Boolean>(false)

    fun checkUserTokenAvailable() {
        launchLite {
            if (spotifyRepository.authToken.isEmpty()) {
                userLoginRequired.postValue(spotifyRepository.authData)
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
            println(song.await())
        }
    }
}