package com.simon.pattern.service

import android.content.Context
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import timber.log.Timber
import javax.inject.Inject

class SpotifyPlayerManager @Inject constructor(
    private val appContext: Context
) {
    private var playerController: SpotifyAppRemote? = null
        set(value) {
            field = value
            field?.playerApi?.toggleRepeat()
            field?.let { Timber.d("Spotify api available: ${it.isConnected}") }
        }

    fun connectToSpotify(clientId: String, redirectUrl: String) {
        val connectionParams = ConnectionParams.Builder(clientId)
            .setRedirectUri(redirectUrl)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(appContext, connectionParams, object : Connector.ConnectionListener {
            override fun onFailure(p0: Throwable?) {
                Timber.e(p0, "Failed to connect to the Spotify Remote")
            }

            override fun onConnected(p0: SpotifyAppRemote?) {
                Timber.d("Connected to spotify remote")
                p0?.let {
                    Timber.d("---Controller captured ---")
                    playerController = it
                }
            }
        })
    }

    fun disconnectFromSpotify() {
        SpotifyAppRemote.disconnect(playerController)
    }

    fun checkSpotifyDownloaded(): Boolean =
        SpotifyAppRemote.isSpotifyInstalled(appContext)

    fun playTrack(trackId: String): Boolean = if (checkControllerAvailable()) {
        Timber.d("Controller available, playing track $trackId")
        playerController?.playerApi?.play(trackId)
        true
    } else {
        Timber.i("Controller not available")
        false
    }

    private fun checkControllerAvailable(): Boolean {
        playerController?.let {
            return it.isConnected
        }
        return false
    }
}
