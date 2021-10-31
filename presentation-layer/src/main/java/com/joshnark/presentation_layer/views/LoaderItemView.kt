package com.joshnark.presentation_layer.views

import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.joshnark.presentation_layer.databinding.ItemLoaderBinding
import com.joshnark.presentation_layer.utils.extensions.addCustomAnimation
import com.joshnark.presentation_layer.utils.extensions.show

class LoaderItemView(private val binding: ItemLoaderBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(state: LoadState, retryCallback: () -> Unit) = with(binding) {
        textViewErrorMessage.show(state is LoadState.Error)
        buttonRetry.show(state is LoadState.Error)
        buttonRetry.setOnClickListener{ retryCallback.invoke() }
        progressBarLoadingRecycler.show(state is LoadState.Loading)
        progressBarLoadingRecycler.addCustomAnimation()
    }

}