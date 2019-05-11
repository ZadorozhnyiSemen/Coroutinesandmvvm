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
import timber.log.Timber

class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainViewModel
    override val layoutId: Int? = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelProvider(viewModelFactory)
        viewModel.userLoginRequired.observe(this, Observer {
            Timber.d("New auth data received ${it.peek()}")
            it.getValueForEvent()?.let { data ->
                requestAccessToken(data)
            }
        })
        viewModel.spotifyServiceReady.observe(this, Observer {
            Timber.d("New token received. Service ready")
            viewModel.checkIfCoroutinesWorking()
        })
        viewModel.checkUserTokenAvailable()
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
        AuthenticationClient.openLoginActivity(this, TOKEN_RESULT_CODE, request)
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
            TOKEN_RESULT_CODE -> {
                Timber.d("Access token from ICP [${response.accessToken}]")
                viewModel.obtainAccessToken(response.accessToken)
            }
            else -> {
                Timber.d("Unhandled activity result with request code: [$requestCode]")
            }
        }
    }

    companion object {
        private const val TOKEN_RESULT_CODE = 124
    }
}
