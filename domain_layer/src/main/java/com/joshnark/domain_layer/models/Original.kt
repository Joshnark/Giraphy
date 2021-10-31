package com.joshnark.domain_layer.models


import com.google.gson.annotations.SerializedName

data class Original(
    @SerializedName("hash")
    val hash: String?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("webp")
    val webp: String?,
)