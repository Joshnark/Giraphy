package com.joshnark.data_layer.data_source

import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Tag

interface TagsDataSource {
    suspend fun getTrendingTags(): GenericResult<List<Tag>>

    suspend fun getSearchRelatedTags(keyword: String): GenericResult<List<Tag>>

    suspend fun getCategories(): GenericResult<List<Tag>>
}