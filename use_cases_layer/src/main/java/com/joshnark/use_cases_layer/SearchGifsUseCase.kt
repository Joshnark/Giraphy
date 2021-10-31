package com.joshnark.use_cases_layer

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.item_types.GifItemType
import com.joshnark.domain_layer.repositories.GifsRepository

class SearchGifsUseCase(private val gifsRepository: GifsRepository) {
    suspend operator fun invoke(parameter: String?): LiveData<PagingData<GifItemType>> = gifsRepository.searchGifs(parameter.orEmpty())
}