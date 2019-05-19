package com.simon.pattern.views.launcher

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.MutableLiveData
import com.simon.pattern.base.CoroutineViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LauncherViewModel @Inject constructor(
    private val appContext: Context
) : CoroutineViewModel() {

    val destinationRoute = MutableLiveData<LaunchDestination>()

    fun checkDestination() {
        viewModelScope.launch(Dispatchers.IO) {
            val sharedPreferences = appContext.getSharedPreferences("pattern_player_prefs", MODE_PRIVATE)
            val complete = sharedPreferences.getBoolean("onboarding_complete", false)
            destinationRoute.postValue(if (complete) LaunchDestination.MAIN else LaunchDestination.ONBOARDING)
        }
    }
}

enum class LaunchDestination {
    ONBOARDING,
    MAIN
}