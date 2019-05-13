package com.simon.pattern.repository

import android.util.Base64
import com.simon.pattern.domain.SearchResult
import com.simon.pattern.rest.SpotifyService
import javax.inject.Inject

class SpotifyRepository @Inject constructor(
    private val spotifyApi: SpotifyService
) {
    private var authorizationTokenAcquired = false
    var authToken: String = ""
        set(value) {
            field = value
            authorizationTokenAcquired = true
        }
    val authData = AuthData(
        decodeKey(loadClientId()),
        decodeKey(loadClientId2()),
        decodeKey(loadRedirectUrl())
    )

    private external fun loadClientId(): String
    private external fun loadClientId2(): String
    private external fun loadRedirectUrl(): String

    private fun decodeKey(key: String) = String(Base64.decode(key, Base64.DEFAULT))

    suspend fun searchSong(songName: String): SearchResult? {
        return if (authorizationTokenAcquired) {
            return spotifyApi.searchTrack("Bearer $authToken", songName)
        } else {
            null
        }
    }

    companion object {
        init {
            System.loadLibrary("key-store")
            System.loadLibrary("lib")
        }
    }
}

class AuthData(
    val clientId: String,
    val clientSecret: String,
    val clientUrl: String,
    val clientUrlCallback: String = "$clientUrl/callback",
    val scopes: Array<String> = arrayOf("user-read-email"),
    val campaign: String = "your-campaign-token"
)