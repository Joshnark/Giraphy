package com.joshnark.data_layer.test_doubles

import com.joshnark.data_layer.data_source.GifsDataSource
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif

class GifsDataSourceTestDouble: GifsDataSource {

    private val favoriteGifs = arrayListOf<Gif>()

    override suspend fun getTrendingGifs(limit: Int, offset: Int): GenericResult<List<Gif>> {
        TODO("Not yet implemented")
    }

    override suspend fun addGifToFavorites(gif: Gif): GenericResult<Gif> {
        val resultGif = gif.copy(isLiked = !gif.isLiked)
        if (favoriteGifs.contains(gif)) {
            favoriteGifs.remove(gif)
        } else {
            favoriteGifs.add(resultGif)
        }
        return GenericResult.Success(resultGif)
    }

    override suspend fun searchGifs(keyword: String): GenericResult<List<Gif>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFavoriteGifs(): GenericResult<List<Gif>> {
        return GenericResult.Success(favoriteGifs)
    }

    override suspend fun getFavoriteGifs(limit: Int, offset: Int): GenericResult<List<Gif>> {
        return GenericResult.Success(favoriteGifs)
    }

}