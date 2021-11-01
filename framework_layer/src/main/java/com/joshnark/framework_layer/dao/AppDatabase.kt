package com.joshnark.framework_layer.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joshnark.domain_layer.models.db_entities.GifEntity

@Database(entities = [GifEntity::class], version = 4, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun savedGifsDao(): SavedGifsDao

}