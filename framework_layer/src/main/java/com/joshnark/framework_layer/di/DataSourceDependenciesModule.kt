package com.joshnark.framework_layer.di

import com.joshnark.data_layer.data_source.GifsDataSource
import com.joshnark.data_layer.data_source.TagsDataSource
import com.joshnark.framework_layer.dao.SavedGifsDao
import com.joshnark.framework_layer.data_sources.GifsDataSourceImpl
import com.joshnark.framework_layer.data_sources.TagsDataSourceImpl
import com.joshnark.framework_layer.services.GifsServices
import com.joshnark.framework_layer.services.TagsServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceDependenciesModule {

    @Provides
    @Singleton
    fun provideGifsDataSource(gifsServices: GifsServices, savedGifsDao: SavedGifsDao): GifsDataSource {
        return GifsDataSourceImpl(gifsServices, savedGifsDao)
    }

    @Provides
    @Singleton
    fun provideTagsDataSource(tagsServices: TagsServices): TagsDataSource {
        return TagsDataSourceImpl(tagsServices)
    }

}