package com.joshnark.presentation_layer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.joshnark.presentation_layer.databinding.ItemLoaderBinding
import com.joshnark.presentation_layer.views.LoaderItemView

class LoadAdapter(private val retryCallback: () -> Unit): LoadStateAdapter<LoaderItemView>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderItemView {
        return LoaderItemView(ItemLoaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LoaderItemView, loadState: LoadState) {
        holder.bind(loadState, retryCallback)
    }

}
