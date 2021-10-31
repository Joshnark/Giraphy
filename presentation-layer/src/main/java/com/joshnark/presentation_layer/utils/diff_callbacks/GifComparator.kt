package com.joshnark.presentation_layer.utils.diff_callbacks

import androidx.recyclerview.widget.DiffUtil
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.item_types.GifItemType

object GifComparator : DiffUtil.ItemCallback<Gif>() {
    override fun areItemsTheSame(oldItem: Gif, newItem: Gif) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif) =
        oldItem == newItem
}