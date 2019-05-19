package com.simon.pattern.di.activities

import androidx.lifecycle.ViewModel
import com.simon.pattern.di.vmodels.ViewModelKey
import com.simon.pattern.views.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}