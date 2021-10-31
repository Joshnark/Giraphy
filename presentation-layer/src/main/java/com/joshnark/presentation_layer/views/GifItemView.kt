package com.joshnark.presentation_layer.views

import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.joshnark.domain_layer.models.Gif
import com.joshnark.presentation_layer.R
import com.joshnark.presentation_layer.databinding.ItemGifBinding
import com.joshnark.presentation_layer.utils.RandomGenerator.getRandomInt
import com.joshnark.presentation_layer.utils.extensions.addCustomAnimation
import com.joshnark.presentation_layer.utils.extensions.hide
import com.joshnark.presentation_layer.utils.extensions.setOnSafeClickListener

class GifItemView(private val binding: ItemGifBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(gif: Gif, onLikeListener: (Gif) -> Unit) = with(binding) {
        loadImage(gif, imageViewGif, progressBarLoadingGif)
        setBackgroundColor(cardViewBackground)
        setUserData(gif, binding.imageViewUserPhoto, binding.textViewUserName)
        setTitle(gif, binding.textViewTitle)
        setLike(gif, binding.buttonLike, onLikeListener)
        progressBarLoadingGif.addCustomAnimation()
    }

    private fun loadImage(gif: Gif, imageViewGif: GlideImageView, progressBar: ProgressBar) {
        imageViewGif.loadImage(
            gif.images?.original?.url ?: gif.images?.original?.webp ?: "",
            onLoadFinished = {progressBar.hide()}
        )
    }

    private fun setBackgroundColor(cardViewBackground: CardView) {
        val context = itemView.context

        val colors: List<Int> = listOf(
            ResourcesCompat.getColor(context.resources, R.color.bittersweet_red, null),
            ResourcesCompat.getColor(context.resources, R.color.pastel_green, null),
            ResourcesCompat.getColor(context.resources, R.color.haze_yellow, null),
            ResourcesCompat.getColor(context.resources, R.color.jordy_blue, null),
            ResourcesCompat.getColor(context.resources, R.color.pigy_pink, null)
        )

        cardViewBackground.setCardBackgroundColor(colors[getRandomInt(5)])
    }

    private fun setUserData(gif: Gif, userImage: GlideImageView, userName: TextView) {
        userImage.loadImage(gif.user?.avatarUrl.orEmpty())
        userName.text = gif.user?.displayName.orEmpty()
    }

    private fun setTitle(gif: Gif, gifTitle: TextView) {
        gifTitle.text = gif.title.orEmpty()
    }

    private fun setLike(gif: Gif, buttonLike: AppCompatImageView, onLikeListener: (Gif) -> Unit) {
        buttonLike.setImageResource(if(gif.isLiked) R.drawable.ic_heart_solid else R.drawable.ic_heart_solid_unfavorited)
        buttonLike.setOnSafeClickListener{
            onLikeListener.invoke(gif)
        }
    }

}