package com.joshnark.data_layer.paging_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joshnark.data_layer.data_source.GifsDataSource
import com.joshnark.domain_layer.models.GenericResult.Success
import com.joshnark.domain_layer.models.GenericResult.Error
import com.joshnark.domain_layer.models.exceptions.NoMoreItemsException
import com.joshnark.domain_layer.models.item_types.GifItemType

class GifsPagingDataSource(
    private val gifsDataSource: GifsDataSource,
    private val keyword: String = ""
    ): PagingSource<Int, GifItemType>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifItemType> {
        val offset = params.key ?: INITIAL_OFFSET
        return try {
            var nextKey: Int? = null
            val data = ArrayList<GifItemType>()

            val gifsReponse = if (keyword.isEmpty()) {
                gifsDataSource.getTrendingGifs(DEFAULT_LIMIT, offset)
            } else {
                gifsDataSource.searchGifs(keyword)
            }

            val favoriteGifsResponse = gifsDataSource.getAllFavoriteGifs()

            val favoritedData = if (favoriteGifsResponse is Success) {
                favoriteGifsResponse.data
            } else emptyList()

            if (offset == INITIAL_OFFSET) {

                val title = if (keyword.isEmpty()) "Trending Gifs" else "Search By:"
                data.add(GifItemType.GifSeparator(title, keyword))
            }

            data.addAll(
                if (gifsReponse is Success) {
                    if (offset == INITIAL_OFFSET && gifsReponse.data.isEmpty()) {
                        throw NoMoreItemsException()
                    }

                    if (gifsReponse.data.size >= DEFAULT_LIMIT) {
                        nextKey = offset + DEFAULT_LIMIT
                    }

                    gifsReponse.data.map {
                        var isLiked = false

                        favoritedData.forEach { favoriteGif ->
                            if (favoriteGif.id == it.id) {
                                isLiked = true
                            }
                        }

                        GifItemType.TrendingGif(it.copy(
                            isLiked = isLiked
                        ))
                    }
                } else {
                    if (gifsReponse is Error) {
                        throw gifsReponse.exception
                    }
                    emptyList()
                }
            )

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifItemType>): Int? = null

    companion object {
        const val DEFAULT_LIMIT = 20
        const val INITIAL_OFFSET = 0
    }

}