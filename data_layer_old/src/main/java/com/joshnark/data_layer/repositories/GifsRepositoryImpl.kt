package com.joshnark.data_layer.repositories

import com.joshnark.data_layer.data_source.GifsDataSource
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.repositories.GifsRepository

class GifsRepositoryImpl(private val gifsDataSource: GifsDataSource): GifsRepository {

    override suspend fun getTrendingGifs(limit: Int, offset: Int): GenericResult<List<Gif>> {
        return gifsDataSource.getTrendingGifs(limit, offset)
    }

    override suspend fun searchGifs(keyword: String): GenericResult<List<Gif>> {
        return gifsDataSource.searchGifs(keyword)
    }

    /*override suspend fun getFavoritedGifs(): GenericResult<List<Gif>> {
        return gifsDataSource.getFavoritedGifs()
    }*/

    override suspend fun getRandomGif(): GenericResult<Gif> {
        return gifsDataSource.getRandomGif()
    }
}