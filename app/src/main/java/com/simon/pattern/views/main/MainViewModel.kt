package com.simon.pattern.views.main

import androidx.lifecycle.MutableLiveData
import com.simon.pattern.base.CoroutineViewModel
import com.simon.pattern.base.SingleEvent
import com.simon.pattern.domain.Track
import com.simon.pattern.repository.AuthData
import com.simon.pattern.repository.SpotifyRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val spotifyRepository: SpotifyRepository
) : CoroutineViewModel() {

    val textWithStatus = MutableLiveData<String>()
    val userLoginRequired = MutableLiveData<SingleEvent<AuthData>>()
    val spotifyServiceReady = MutableLiveData<Boolean>(false)
    val searchResult = MutableLiveData<List<Track>>(listOf())
    val topTrack = MutableLiveData<SingleEvent<Track>>()

    fun checkUserTokenAvailable() {
        launchLite {
            if (spotifyRepository.authToken.isEmpty()) {
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

    fun onSearchQueryChanged(query: String) {
        launchLite {
            val callResult = searchTrackOnce(query)
            searchResult.postValue(callResult)
            if (callResult.isNotEmpty()) {
                topTrack.postValue(SingleEvent(callResult.first()))
            }
        }
    }

    private suspend fun searchTrackOnce(query: String): List<Track> {
        val searchResult = spotifyRepository.searchSong(query)
        return if (searchResult == null) {
            textWithStatus.postValue("Search for son returns null")
            emptyList()
        } else {
            searchResult.tracks.items
        }
    }
}