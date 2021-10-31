package com.joshnark.domain_layer.repositories

import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Tag

interface TagsRepository {

    suspend fun getCategories(): GenericResult<List<Tag>>

}