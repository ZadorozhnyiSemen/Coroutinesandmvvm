package com.simon.pattern.domain

import com.google.gson.annotations.SerializedName

class Album(
    @SerializedName("album_type") val type: String,
    val images: List<Image>,
    val uri: String
)