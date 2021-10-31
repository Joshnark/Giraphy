package com.joshnark.domain_layer.models


import com.google.gson.annotations.SerializedName

data class PreviewGif(
    @SerializedName("height")
    val height: String?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: String?
)