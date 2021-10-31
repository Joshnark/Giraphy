package com.joshnark.data_layer.data_source

import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif

interface GifsDataSource {
    suspend fun getTrendingGifs(limit: Int, offset: Int): GenericResult<List<Gif>>

    suspend fun searchGifs(keyword: String): GenericResult<List<Gif>>

    suspend fun getRandomGif(): GenericResult<Gif>
}