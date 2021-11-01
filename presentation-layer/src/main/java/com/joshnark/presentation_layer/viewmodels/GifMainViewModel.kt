package com.joshnark.presentation_layer.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.Tag
import com.joshnark.domain_layer.models.item_types.GifItemType
import com.joshnark.use_cases_layer.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifMainViewModel @Inject constructor(
    private val getTrendingGifsUseCase: GetTrendingGifsUseCase,
    private val addGifToFavoritesUseCase: AddGifToFavoritesUseCase,
    private val getFavoritedGifsUseCase: GetFavoritedGifsUseCase,
    private val searchGifsUseCase: SearchGifsUseCase
) : ViewModel() {

    private val _gifFavoritedLiveData = MutableLiveData<GenericResult<Gif>?>()
    val gifFavoritedLiveData: LiveData<GenericResult<Gif>?> = _gifFavoritedLiveData

    suspend fun getGifs(keyword: String): LiveData<PagingData<GifItemType>> {
        return if (keyword.isEmpty()) {
            getTrendingGifsUseCase()
        } else {
            searchGifsUseCase(keyword)
        }
    }

    suspend fun getFavoritedGifs() = getFavoritedGifsUseCase()

    fun addGifToFavorites(gif: Gif) {
        viewModelScope.launch {
            _gifFavoritedLiveData.value = addGifToFavoritesUseCase(gif)
        }
    }

}