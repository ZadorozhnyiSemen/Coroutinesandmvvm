package com.simon.pattern.rest

import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SpotifyService {

    @GET("search?type=track")
    fun searchTrack(
        @Header("Authorization") token: String,
        @Query("q") searchQuery: String
    ): Deferred<JsonObject>

}