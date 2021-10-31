package com.joshnark.use_cases_layer

import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.Tag
import com.joshnark.domain_layer.repositories.GifsRepository
import com.joshnark.domain_layer.repositories.TagsRepository

class AddGifToFavoritesUseCase(private val gifsRepository: GifsRepository) {
    suspend operator fun invoke(gif: Gif): GenericResult<Gif> = gifsRepository.addGifToFavorites(gif)
}