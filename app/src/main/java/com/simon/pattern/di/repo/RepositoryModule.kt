package com.simon.pattern.di.repo

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.simon.pattern.rest.SpotifyService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RepositoryModule {

    @Provides
    fun providesSpotifyService(okHttpClient: OkHttpClient): SpotifyService {
        return Retrofit.Builder()
            .baseUrl("https://api.spotify.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(SpotifyService::class.java)
    }

    @Provides
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            })
            .build()
    }
}