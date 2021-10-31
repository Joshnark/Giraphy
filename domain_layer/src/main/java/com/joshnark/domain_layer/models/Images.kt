package com.joshnark.domain_layer.models


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("original")
    val original: Original,
    @SerializedName("original_still")
    val originalStill: Original,
    @SerializedName("preview_gif")
    val previewGif: PreviewGif,
)