package com.joshnark.domain_layer.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("display_name")
    val displayName: String?,
): Parcelable