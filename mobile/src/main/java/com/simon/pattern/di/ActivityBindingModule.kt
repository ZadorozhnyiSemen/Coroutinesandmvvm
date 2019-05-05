package com.simon.pattern.di

import com.simon.pattern.di.activities.MainModule
import com.simon.pattern.views.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun mainActivity(): MainActivity
}