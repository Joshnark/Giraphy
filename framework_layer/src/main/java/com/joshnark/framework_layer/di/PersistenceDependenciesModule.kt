package com.joshnark.framework_layer.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.joshnark.framework_layer.dao.AppDatabase
import com.joshnark.framework_layer.dao.SavedGifsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistenceDependenciesModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSavedGifsDao(appDatabase: AppDatabase): SavedGifsDao = appDatabase.savedGifsDao()

    companion object {

        private const val DATABASE_NAME = "SUPER_GIF_APP_DATABASE"
        private const val SHARED_PREFERENCES_NAME = "SUPER_GIF_APP_SHARED_PREFERENCES"

    }

}