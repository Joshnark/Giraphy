package com.joshnark.framework_layer.data_sources

import com.joshnark.data_layer.data_source.TagsDataSource
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Tag
import com.joshnark.framework_layer.services.TagsServices
import com.joshnark.framework_layer.utils.processResult

class TagsDataSourceImpl(private val tagsServices: TagsServices): TagsDataSource {

    override suspend fun getCategories(): GenericResult<List<Tag>> {
        return processResult { tagsServices.getCategories().data }
    }

}