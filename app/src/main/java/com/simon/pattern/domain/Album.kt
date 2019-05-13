package com.simon.pattern.domain

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("album_type") val type: String,
    val name: String,
    val images: List<Image>,
    val uri: String
)