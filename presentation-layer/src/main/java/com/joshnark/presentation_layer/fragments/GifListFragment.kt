package com.joshnark.presentation_layer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.joshnark.domain_layer.models.GenericResult.Success
import com.joshnark.domain_layer.models.GenericResult.Error
import com.joshnark.domain_layer.models.Tag
import com.joshnark.domain_layer.models.exceptions.NoMoreItemsException
import com.joshnark.presentation_layer.CategoriesViewModel
import com.joshnark.presentation_layer.activities.MainActivity
import com.joshnark.presentation_layer.adapters.GifListAdapter
import com.joshnark.presentation_layer.adapters.LoadAdapter
import com.joshnark.presentation_layer.databinding.FragmentGifListBinding
import com.joshnark.presentation_layer.utils.extensions.*
import com.joshnark.presentation_layer.utils.extensions.addCustomAnimation
import com.joshnark.presentation_layer.utils.extensions.hide
import com.joshnark.presentation_layer.utils.extensions.show
import com.joshnark.presentation_layer.viewmodels.GifMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GifListFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private val binding by viewBinding(FragmentGifListBinding::inflate)

    private val gifMainViewModel: GifMainViewModel by activityViewModels()
    private val categoriesViewModel: CategoriesViewModel by activityViewModels()

    private var gifsAdapter: GifListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setGifsList()
        setSearchEditTextFunctionalities()
        observeLiveData()
        observeGifPagingLiveData()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        observeGifPagingLiveData(item.title.toString())
        gifsAdapter?.refresh()
        return false
    }

    private fun observeGifPagingLiveData(keyword: String = "") {
        viewLifecycleOwner.lifecycleScope.launch {
            gifMainViewModel.getGifs(keyword).observe(viewLifecycleOwner, {
                it?.let {
                    gifsAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
                }
            })
        }
    }

    private fun observeLiveData() {
        gifMainViewModel.gifFavoritedLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    gifsAdapter?.updateLike(it.data)
                }
                is Error -> {
                    Toast.makeText(requireContext(), "An error ocurred", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }

        categoriesViewModel.categoriesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    setParentNavigationDrawerListener(it.data)
                }
                is Error -> {
                    Toast.makeText(requireContext(), "Error loading categories", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }

    private fun setGifsList() {
        gifsAdapter = GifListAdapter(gifMainViewModel::addGifToFavorites)

        binding.viewError.buttonRetry.setOnClickListener {
            gifsAdapter?.retry()
        }

        binding.recyclerViewTrendingGifs.apply {
            adapter = gifsAdapter?.withLoadStateFooter(
                footer = LoadAdapter{ gifsAdapter?.retry() }
            )

            gifsAdapter?.addLoadStateListener {
                binding.viewLoading.root.hide()
                binding.viewError.root.hide()
                binding.recyclerViewTrendingGifs.show()
                if (it.refresh is LoadState.Loading) {
                    showGifLoaders()
                    binding.recyclerViewTrendingGifs.hide()
                } else if (it.refresh is LoadState.Error) {
                    showError((it.refresh as LoadState.Error).error)
                    binding.recyclerViewTrendingGifs.hide()
                }
            }

            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun setSearchEditTextFunctionalities() {
        binding.viewHeader.buttonStartSearch.setOnClickListener {
            showSearUI(true)
        }

        binding.viewHeader.buttonEndSearch.setOnClickListener {
            showSearUI(false)
            observeGifPagingLiveData()
            gifsAdapter?.refresh()
            requireView().closeKeyboard()
        }

        binding.viewHeader.editTextSearch.afterTextChanged {
            observeGifPagingLiveData(it)
            gifsAdapter?.refresh()
        }
    }

    private fun showSearUI(show: Boolean) {
        binding.viewHeader.editTextSearch.apply {
            fade(show)
            openKeyboardAndFocus()
            setText("")
        }
        binding.viewHeader.buttonEndSearch.fade(show)
        binding.viewHeader.buttonStartSearch.fade(!show)
        binding.viewHeader.buttonCategories.fade(!show)
    }

    private fun showGifLoaders() {
        binding.viewLoading.progressBarLoadingRecycler.addCustomAnimation()
        binding.viewLoading.root.show()
    }

    private fun showError(exception: Throwable) {
        binding.viewError.root.show()
        when(exception) {
            is NoMoreItemsException -> {
                binding.viewError.textViewErrorMessage.text = "No items to load xD"
            }
            else -> binding.viewError.textViewErrorMessage.text = "Looks like an error happened :c"
        }
    }

    private fun setParentNavigationDrawerListener(categories: List<Tag>) {
        (requireActivity() as? MainActivity)?.setNavigationDrawerListener(categories,this)
    }

}