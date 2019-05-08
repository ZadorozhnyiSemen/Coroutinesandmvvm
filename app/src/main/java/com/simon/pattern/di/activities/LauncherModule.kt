package com.simon.pattern.di.activities

import androidx.lifecycle.ViewModel
import com.simon.pattern.di.vmodels.ViewModelKey
import com.simon.pattern.views.launcher.LauncherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class LauncherModule {

    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel::class)
    internal abstract fun bindLauncherViewModel(viewModel: LauncherViewModel): ViewModel
}