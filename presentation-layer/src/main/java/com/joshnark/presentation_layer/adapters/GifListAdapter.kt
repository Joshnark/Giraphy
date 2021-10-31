package com.joshnark.presentation_layer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.item_types.GifItemType
import com.joshnark.presentation_layer.databinding.ItemGifBinding
import com.joshnark.presentation_layer.databinding.ItemSeparatorBinding
import com.joshnark.presentation_layer.utils.diff_callbacks.GifItemTypeComparator
import com.joshnark.presentation_layer.views.GifItemView
import com.joshnark.presentation_layer.views.SeparatorView
import java.lang.UnsupportedOperationException

class GifListAdapter(private val onLikeListener: (Gif) -> Unit): PagingDataAdapter<GifItemType, RecyclerView.ViewHolder>(GifItemTypeComparator) {

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is GifItemType.TrendingGif -> TRENDING_GIF
            is GifItemType.GifSeparator -> GIF_SEPARATOR
            else -> throw UnsupportedOperationException()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TRENDING_GIF -> GifItemView(ItemGifBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            GIF_SEPARATOR -> SeparatorView(ItemSeparatorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw UnsupportedOperationException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when(val item = getItem(position)) {
            is GifItemType.TrendingGif -> (holder as GifItemView).bind(item.gif, onLikeListener)
            is GifItemType.GifSeparator -> (holder as SeparatorView).bind(item.title, item.subTitle)
            else -> throw UnsupportedOperationException()
        }
    }

    //this is not the best approach to update an item since I am breaking inmutability, but I simply dont have enough time to develop the proper solution
    fun updateLike(gif: Gif) {
        for(i in 0 until itemCount) {
            val item = getItem(i)
            if (item is GifItemType.TrendingGif && item.gif.id == gif.id) {
                (getItem(i) as GifItemType.TrendingGif).gif.isLiked = !item.gif.isLiked
                notifyItemChanged(i)
                return
            }
        }
    }

    companion object {
        internal const val TRENDING_GIF = 5
        internal const val GIF_SEPARATOR = 7
    }

}
