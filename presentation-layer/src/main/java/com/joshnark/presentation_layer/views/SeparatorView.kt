package com.joshnark.presentation_layer.views

import androidx.recyclerview.widget.RecyclerView
import com.joshnark.presentation_layer.databinding.ItemSeparatorBinding
import com.joshnark.presentation_layer.utils.extensions.hide
import com.joshnark.presentation_layer.utils.extensions.show

class SeparatorView(private val binding: ItemSeparatorBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(title: String, subtitle: String = "") = with(binding) {
        binding.textViewSeparatorTitle.text = title
        if (subtitle.isEmpty()) {
            binding.textViewSeparatorSubTitle.hide()
        } else {
            binding.textViewSeparatorSubTitle.text = subtitle
            binding.textViewSeparatorSubTitle.show()
        }
    }

}