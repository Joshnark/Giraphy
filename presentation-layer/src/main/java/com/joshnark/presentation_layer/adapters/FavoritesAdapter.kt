package com.joshnark.presentation_layer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.joshnark.domain_layer.models.Gif
import com.joshnark.presentation_layer.databinding.ItemGifFavoriteBinding
import com.joshnark.presentation_layer.utils.diff_callbacks.GifComparator
import com.joshnark.presentation_layer.views.GifFavoriteItemView

class FavoritesAdapter(private val onLikeListener: (Gif) -> Unit): PagingDataAdapter<Gif, GifFavoriteItemView>(GifComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifFavoriteItemView {
        return GifFavoriteItemView(ItemGifFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GifFavoriteItemView, position: Int) {
        getItem(position)?.let { holder.bind(it, onLikeListener) }
    }

}
