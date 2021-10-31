package com.joshnark.data_layer.paging_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joshnark.data_layer.data_source.GifsDataSource
import com.joshnark.domain_layer.models.GenericResult.Success
import com.joshnark.domain_layer.models.GenericResult.Error
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.exceptions.NoMoreItemsException

class FavoritedGifsPagingDataSource(private val gifsDataSource: GifsDataSource): PagingSource<Int, Gif>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        val offset = params.key ?: INITIAL_OFFSET
        return try {
            val response = gifsDataSource.getFavoriteGifs(DEFAULT_LIMIT, offset)
            var nextKey: Int? = null

            val data = if (response is Success) {
                if (response.data.isEmpty() && offset == INITIAL_OFFSET) {
                    throw NoMoreItemsException()
                } else if (response.data.size > DEFAULT_LIMIT) {
                    nextKey = offset + DEFAULT_LIMIT
                }
                response.data
            } else {
                if (response is Error) {
                    throw response.exception
                }
                emptyList()
            }

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? = null

    companion object {
        const val DEFAULT_LIMIT = 50
        const val INITIAL_OFFSET = 0
    }

}