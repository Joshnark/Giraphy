package com.joshnark.domain_layer.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    @SerializedName("original")
    val original: Original,
    @SerializedName("preview_gif")
    val previewGif: PreviewGif
): Parcelable