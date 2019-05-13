package com.simon.pattern.domain

data class Track(
    val id: String,
    val name: String,
    val uri: String,
    val album: Album,
    val artists: List<Artist>
)
