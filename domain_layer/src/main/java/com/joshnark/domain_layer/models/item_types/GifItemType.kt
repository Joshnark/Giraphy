package com.joshnark.domain_layer.models.item_types

import com.joshnark.domain_layer.models.Gif

sealed class GifItemType(
    val id: String
) {
    data class TrendingGif(val gif: Gif): GifItemType(gif.id)
    data class GifSeparator(val title: String, val subTitle: String): GifItemType(title)

}