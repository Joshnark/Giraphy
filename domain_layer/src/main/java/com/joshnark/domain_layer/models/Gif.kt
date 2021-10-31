package com.joshnark.domain_layer.models

import com.google.gson.annotations.SerializedName

data class Gif(
    @SerializedName("id")
    val id: String,
    @SerializedName("embed_url")
    val embedUrl: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("images")
    val images: Images? = null,
    @SerializedName("user")
    val user: User? = null,

    val cachedUrl: String?,
    var isLiked: Boolean
)