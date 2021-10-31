package com.joshnark.presentation_layer.utils.diff_callbacks

import androidx.recyclerview.widget.DiffUtil
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.Tag

object TagComparator : DiffUtil.ItemCallback<Tag>() {
    override fun areItemsTheSame(oldItem: Tag, newItem: Tag) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Tag, newItem: Tag) =
        oldItem == newItem
}