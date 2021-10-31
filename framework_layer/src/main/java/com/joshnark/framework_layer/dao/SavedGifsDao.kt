package com.joshnark.framework_layer.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.joshnark.domain_layer.models.db_entities.GifEntity

@Dao
interface SavedGifsDao {

    @Insert
    suspend fun insert(gif: GifEntity)

    @Update
    suspend fun update(gif: GifEntity)

    @Delete
    suspend fun delete(gif: GifEntity)

    @Query("SELECT * FROM saved_gifs WHERE id = :id")
    suspend fun select(id: String): List<GifEntity>

    @Query("SELECT * FROM saved_gifs")
    suspend fun selectAll(): List<GifEntity>

    @Query("SELECT * FROM saved_gifs LIMIT :limit OFFSET :offset")
    suspend fun selectAllWithPaging(limit: Int, offset: Int): List<GifEntity>

}