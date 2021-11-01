package com.joshnark.domain_layer.test_doubles

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.item_types.GifItemType
import com.joshnark.domain_layer.repositories.GifsRepository

class GifsRespositoryTestDouble: GifsRepository {

    override suspend fun getTrendingGifs(): LiveData<PagingData<GifItemType>> {
        TODO("Not yet implemented")
    }

    override suspend fun addGifToFavorites(gif: Gif): GenericResult<Gif> {
        TODO("Not yet implemented")
    }

    override suspend fun searchGifs(keyword: String): LiveData<PagingData<GifItemType>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteGifs(): LiveData<PagingData<Gif>> {
        TODO("Not yet implemented")
    }
}