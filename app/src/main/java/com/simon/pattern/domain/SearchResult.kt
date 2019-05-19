package com.simon.pattern.domain

interface SearchItems<out T : SearchType> {
    fun pullItems(): T
}

open class SearchType

data class SearchResult(
    val tracks: SearchTracks
)

data class SearchTracks(
    val items: List<Track>
)