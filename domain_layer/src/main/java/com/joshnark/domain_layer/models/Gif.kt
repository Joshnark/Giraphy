package com.joshnark.domain_layer.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gif(
    @SerializedName("id")
    val id: String,
    @SerializedName("embed_url")
    val embedUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("images")
    val images: Images? = null,
    @SerializedName("user")
    val user: User? = null,
    @SerializedName("source_tld")
    val source: String?,

    val cachedUrl: String?,
    var isLiked: Boolean
): Parcelable