package com.joshnark.presentation_layer.utils.diff_callbacks

import androidx.recyclerview.widget.DiffUtil
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.item_types.GifItemType

object GifItemTypeComparator : DiffUtil.ItemCallback<GifItemType>() {
    override fun areItemsTheSame(oldItem: GifItemType, newItem: GifItemType) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: GifItemType, newItem: GifItemType) =
        oldItem == newItem
}