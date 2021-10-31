package com.joshnark.presentation_layer.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.joshnark.presentation_layer.fragments.FavoritesFragment
import com.joshnark.presentation_layer.fragments.GifListFragment

class MainViewPagerStateAdapter(
        activity: AppCompatActivity,
        private val fragments: List<Fragment>
    ): FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int = fragments.size

}