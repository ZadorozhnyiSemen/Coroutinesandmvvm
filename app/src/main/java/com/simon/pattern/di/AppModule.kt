package com.simon.pattern.di

import android.content.Context
import com.simon.pattern.MainApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideAppContext(application: MainApplication): Context =
        application.applicationContext
}