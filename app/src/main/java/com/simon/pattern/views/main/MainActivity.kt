package com.simon.pattern.views.main

import android.os.Bundle
import com.simon.pattern.R
import com.simon.pattern.base.BaseActivity
import com.simon.pattern.utils.viewModelProvider
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import timber.log.Timber

class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainViewModel
    override val layoutId: Int? = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelProvider(viewModelFactory)
        subscribeToData()
        viewModel.checkIfCoroutinesWorking()
    }

    private fun subscribeToData() {
        val connectionParams = ConnectionParams.Builder("4b4c241b697149978b2824480b6aec7c")
            .showAuthView(true)
            .setRedirectUri("http://replay-app-login/callback")
            .build()

        SpotifyAppRemote.connect(this, connectionParams, object : Connector.ConnectionListener {
            override fun onFailure(p0: Throwable?) {
                Timber.e(p0)
            }

            override fun onConnected(p0: SpotifyAppRemote?) {
                Timber.d("Connected")

            }

        })


    }
}
