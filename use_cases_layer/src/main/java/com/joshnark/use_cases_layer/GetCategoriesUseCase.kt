package com.joshnark.use_cases_layer

import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.Tag
import com.joshnark.domain_layer.repositories.TagsRepository

class GetCategoriesUseCase(private val tagsRepository: TagsRepository) {
    suspend operator fun invoke(): GenericResult<List<Tag>> = tagsRepository.getCategories()
}