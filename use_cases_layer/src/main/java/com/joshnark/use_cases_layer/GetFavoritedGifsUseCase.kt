package com.joshnark.use_cases_layer

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.db_entities.GifEntity
import com.joshnark.domain_layer.repositories.GifsRepository

class GetFavoritedGifsUseCase(private val gifsRepository: GifsRepository) {
    suspend operator fun invoke(): LiveData<PagingData<Gif>> = gifsRepository.getFavoriteGifs()
}