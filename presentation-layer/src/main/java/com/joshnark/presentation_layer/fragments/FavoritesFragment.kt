package com.joshnark.presentation_layer.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.exceptions.NoMoreItemsException
import com.joshnark.presentation_layer.adapters.FavoritesAdapter
import com.joshnark.presentation_layer.adapters.LoadAdapter
import com.joshnark.presentation_layer.databinding.FragmentFavoritesBinding
import com.joshnark.presentation_layer.utils.extensions.*
import com.joshnark.presentation_layer.viewmodels.GifMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val binding by viewBinding(FragmentFavoritesBinding::inflate)

    private val gifMainViewModel: GifMainViewModel by activityViewModels()

    private var favoritesAdapter: FavoritesAdapter? = null

    private var configuration: Configuration? = null

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
        observeLiveData()
    }

    override fun onResume() {
        super.onResume()
        favoritesAdapter?.refresh()
    }

    private fun observeLiveData() {
        viewLifecycleOwner.lifecycleScope.launch {
            gifMainViewModel.getFavoritedGifs().observe(viewLifecycleOwner, {
                favoritesAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
            })
        }

        gifMainViewModel.gifFavoritedLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is GenericResult.Success -> {
                    favoritesAdapter?.refresh()
                }
                is GenericResult.Error -> {
                    Toast.makeText(requireContext(), "An error ocurred", Toast.LENGTH_SHORT).show()
                }
                else -> {
                }
            }
        })
    }

    private fun setGifsList() {
        favoritesAdapter = FavoritesAdapter(gifMainViewModel::addGifToFavorites)

        binding.viewError.buttonRetry.setOnClickListener {
            favoritesAdapter?.retry()
        }

        binding.recyclerViewFavoriteGifs.apply {
            layoutManager = GridLayoutManager(context, getSpan(), RecyclerView.VERTICAL, false)

            adapter = favoritesAdapter?.withLoadStateFooter(
                footer = LoadAdapter { favoritesAdapter?.retry() }
            )

            favoritesAdapter?.addLoadStateListener {
                if (it.refresh is LoadState.Error) {
                    binding.recyclerViewFavoriteGifs.hide()
                    showError((it.refresh as LoadState.Error).error)
                } else {
                    binding.recyclerViewFavoriteGifs.show()
                    binding.viewLoading.root.hide()
                    binding.viewError.root.hide()
                }
            }
        }
    }

    private fun getSpan(): Int {
        return if(getOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
            SPAN_SMALL
        } else {
            SPAN_LARGE
        }
    }

    private fun showError(exception: Throwable) {
        binding.viewError.root.show()
        when (exception) {
            is NoMoreItemsException -> {
                binding.viewError.textViewErrorMessage.text = "You don't have favorites yet"
                binding.viewError.buttonRetry.hide()
            }
            else -> {
                binding.viewError.textViewErrorMessage.text = "Uh Oh! \n Looks like an error ocurred :c"
                binding.viewError.buttonRetry.show()
            }
        }
    }

    companion object {
        private const val SPAN_SMALL = 4
        private const val SPAN_LARGE = 2
    }

}