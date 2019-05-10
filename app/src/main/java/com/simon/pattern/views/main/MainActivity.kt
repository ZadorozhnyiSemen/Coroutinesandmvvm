package com.simon.pattern.views.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.simon.pattern.R
import com.simon.pattern.base.BaseActivity
import com.simon.pattern.repository.AuthData
import com.simon.pattern.utils.viewModelProvider
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse

class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainViewModel
    override val layoutId: Int? = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelProvider(viewModelFactory)
        viewModel.userLoginRequired.observe(this, Observer {
            requestAccessToken(it)
        })
        viewModel.spotifyServiceReady.observe(this, Observer {
            if (it) println("------------Token aquired-----------")
            viewModel.checkIfCoroutinesWorking()
        })
        viewModel.checkUserTokenAvailable()
        //viewModel.checkIfCoroutinesWorking()
    }

    private fun subscribeToData() {
        viewModel.textWithStatus.observe(this, Observer {
            println(it)
        })
        val connectionParams = ConnectionParams.Builder("4b4c241b697149978b2824480b6aec7c")
            .showAuthView(true)
            .setRedirectUri("http://replay-app-login/callback")
            .build()

        //val clientId = String(Base64.decode(getClientId(), Base64.DEFAULT))
        //println(clientId)

        //requestAccessToken(clientId)

//        SpotifyAppRemote.connect(this, connectionParams, object : Connector.ConnectionListener {
//            override fun onFailure(p0: Throwable?) {
//                Timber.e(p0)
//            }
//
//            override fun onConnected(p0: SpotifyAppRemote?) {
//                Timber.d("Connected")
//
//            }
//
//        })
    }

    private fun requestAccessToken(authData: AuthData) {
        val request = getAuthRequest(AuthenticationResponse.Type.TOKEN, authData)
        AuthenticationClient.openLoginActivity(this, 123, request)
    }

    private fun getAuthRequest(
        type: AuthenticationResponse.Type,
        authData: AuthData
    ): AuthenticationRequest {
        println(authData)
        return AuthenticationRequest.Builder(authData.clientId, type, authData.clientUrl)
            .setShowDialog(false)
            .setScopes(authData.scopes)
            .setCampaign(authData.campaign)
            .build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val response = AuthenticationClient.getResponse(resultCode, data)

        when (requestCode) {
            123 -> {
                println(response.accessToken)
                viewModel.obtainAccessToken(response.accessToken)
            }
            else -> {
                println("token not here")
            }
        }
    }



    companion object {
        init {
            System.loadLibrary("key-store")
        }
    }
}
