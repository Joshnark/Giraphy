package com.joshnark.use_cases_layer

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.item_types.GifItemType
import com.joshnark.domain_layer.repositories.GifsRepository

class GetTrendingGifsUseCase(private val gifsRepository: GifsRepository) {
    suspend operator fun invoke(): LiveData<PagingData<GifItemType>> = gifsRepository.getTrendingGifs()
}