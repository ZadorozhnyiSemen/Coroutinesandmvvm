package com.simon.pattern.rest

import com.simon.pattern.domain.SearchResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SpotifyService {

    @GET("search?type=track")
    suspend fun searchTrack(
        @Header("Authorization") token: String,
        @Query("q") searchQuery: String
    ): SearchResult
}
