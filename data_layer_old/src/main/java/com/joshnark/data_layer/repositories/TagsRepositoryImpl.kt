package com.joshnark.data_layer.repositories

import com.joshnark.data_layer.data_source.TagsDataSource
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Tag
import com.joshnark.domain_layer.repositories.TagsRepository

class TagsRepositoryImpl(private val tagsDataSource: TagsDataSource): TagsRepository {

    override suspend fun getTrendingTags(): GenericResult<List<Tag>> {
        return tagsDataSource.getTrendingTags()
    }

    override suspend fun getSearchRelatedTags(keyword: String): GenericResult<List<Tag>> {
        return tagsDataSource.getSearchRelatedTags(keyword)
    }

    override suspend fun getCategories(): GenericResult<List<Tag>> {
        return tagsDataSource.getCategories()
    }
}