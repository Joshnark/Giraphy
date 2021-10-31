package com.joshnark.domain_layer.models

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("name")
    val name: String?,
    @SerializedName("name_encoded")
    val nameEncoded: String? = ""
)