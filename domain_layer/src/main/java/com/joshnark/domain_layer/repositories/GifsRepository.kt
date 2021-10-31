package com.joshnark.domain_layer.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.db_entities.GifEntity
import com.joshnark.domain_layer.models.item_types.GifItemType

interface GifsRepository {

    suspend fun getTrendingGifs(): LiveData<PagingData<GifItemType>>

    suspend fun addGifToFavorites(gif: Gif): GenericResult<Gif>

    suspend fun searchGifs(keyword: String): LiveData<PagingData<GifItemType>>

    suspend fun getFavoriteGifs(): LiveData<PagingData<Gif>>

}