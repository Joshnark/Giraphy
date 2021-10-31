package com.joshnark.framework_layer.data_sources

import androidx.paging.PagingSource
import com.joshnark.data_layer.data_source.GifsDataSource
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif
import com.joshnark.framework_layer.dao.SavedGifsDao
import com.joshnark.domain_layer.models.db_entities.GifEntity
import com.joshnark.framework_layer.services.GifsServices
import com.joshnark.framework_layer.utils.processResult

class GifsDataSourceImpl(private val gifsServices: GifsServices, private val savedGifsDao: SavedGifsDao) : GifsDataSource {

    override suspend fun getTrendingGifs(limit: Int, offset: Int): GenericResult<List<Gif>> {
        return processResult { gifsServices.getTrendingGifs(limit, offset).data }
    }

    override suspend fun addGifToFavorites(gif: Gif): GenericResult<Gif> {
        return processResult {
            if (gif.isLiked) {
                savedGifsDao.delete(GifEntity.fromGif(gif))
            } else {
                if (savedGifsDao.select(gif.id).isEmpty()) {
                    savedGifsDao.insert(GifEntity.fromGif(gif.copy(isLiked = !gif.isLiked)))
                } else {
                    savedGifsDao.update(GifEntity.fromGif(gif.copy(isLiked = !gif.isLiked)))
                }
            }
            gif.copy(
                isLiked = !gif.isLiked
            )
        }
    }

    override suspend fun searchGifs(keyword: String): GenericResult<List<Gif>> {
        return processResult { gifsServices.searchGifs(keyword).data }
    }

    override suspend fun getAllFavoriteGifs(): GenericResult<List<Gif>> {
        return processResult { savedGifsDao.selectAll().map { it.toGif() } }
    }

    override suspend fun getFavoriteGifs(limit: Int, offset: Int): GenericResult<List<Gif>> {
        return processResult { savedGifsDao.selectAllWithPaging(limit, offset).map { it.toGif() } }
    }

}