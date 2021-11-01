package com.joshnark.domain_layer.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PreviewGif(
    @SerializedName("url")
    val url: String?
): Parcelable