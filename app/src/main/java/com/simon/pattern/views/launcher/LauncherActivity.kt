package com.simon.pattern.views.launcher

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.simon.pattern.base.BaseActivity
import com.simon.pattern.utils.viewModelProvider
import com.simon.pattern.views.main.MainActivity
import timber.log.Timber

class LauncherActivity : BaseActivity() {
    private lateinit var viewModel: LauncherViewModel
    override val layoutId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelProvider(viewModelFactory)

        viewModel.checkDestination()

        viewModel.destinationRoute.observe(this, Observer {
            when (it) {
                LaunchDestination.MAIN -> {
                    Timber.d("Starting MAIN")
                    startActivity(Intent(this@LauncherActivity, MainActivity::class.java))
                }
                LaunchDestination.ONBOARDING -> {
                    Timber.d("Starting ONBOARDING")
                    startActivity(Intent(this@LauncherActivity, MainActivity::class.java))
//                    startActivity(Intent(this@LauncherActivity, OnboardingActivity::class.java))
                }
                else -> {
                    // Unexpected or null item
                }
            }
        })
    }
}
