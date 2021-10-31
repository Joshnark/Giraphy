package com.joshnark.presentation_layer.activities

import android.os.Bundle
import android.view.Menu
import android.view.SubMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.joshnark.domain_layer.models.Tag
import com.joshnark.presentation_layer.R
import com.joshnark.presentation_layer.adapters.MainViewPagerStateAdapter
import com.joshnark.presentation_layer.databinding.ActivityMainBinding
import com.joshnark.presentation_layer.fragments.FavoritesFragment
import com.joshnark.presentation_layer.fragments.GifListFragment
import com.joshnark.presentation_layer.utils.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val fragments = listOf(
        GifListFragment(),
        FavoritesFragment()
    )

    private val icons = listOf(
        R.drawable.ic_house_solid,
        R.drawable.ic_heart_solid
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewPager()
    }

    private fun setViewPager() {
        binding.viewPagerMain.adapter = MainViewPagerStateAdapter(this, fragments)
        TabLayoutMediator(binding.tabLayout, binding.viewPagerMain) { tab, position ->
            tab.icon = AppCompatResources.getDrawable(this, icons[position])
        }.attach()
    }

    fun setNavigationDrawerListener(categories: List<Tag>, listener: NavigationView.OnNavigationItemSelectedListener) {
        val menu: Menu = binding.navigationView.menu
        val categoriesSubMenu = menu.addSubMenu("Categories")
        categories.forEach {
            categoriesSubMenu.add(it.name)
        }
        binding.navigationView.setNavigationItemSelectedListener(listener)
    }

}