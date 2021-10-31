package com.joshnark.data_layer.repositories

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.joshnark.data_layer.data_source.GifsDataSource
import com.joshnark.data_layer.paging_sources.FavoritedGifsPagingDataSource
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.repositories.GifsRepository
import com.joshnark.data_layer.paging_sources.GifsPagingDataSource
import com.joshnark.domain_layer.models.item_types.GifItemType

class GifsRepositoryImpl(private val gifsDataSource: GifsDataSource): GifsRepository {

    override suspend fun getTrendingGifs(): LiveData<PagingData<GifItemType>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                GifsPagingDataSource(gifsDataSource)
            }
        ).liveData
    }

    override suspend fun addGifToFavorites(gif: Gif): GenericResult<Gif> {
        return gifsDataSource.addGifToFavorites(gif)
    }

    override suspend fun searchGifs(keyword: String): LiveData<PagingData<GifItemType>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                GifsPagingDataSource(gifsDataSource, keyword)
            }
        ).liveData
    }

    override suspend fun getFavoriteGifs(): LiveData<PagingData<Gif>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50
            ),
            pagingSourceFactory = {
                FavoritedGifsPagingDataSource(gifsDataSource)
            }
        ).liveData
    }
}