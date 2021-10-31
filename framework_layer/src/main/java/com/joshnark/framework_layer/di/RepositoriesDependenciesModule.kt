package com.joshnark.framework_layer.di

import com.joshnark.data_layer.data_source.GifsDataSource
import com.joshnark.data_layer.data_source.TagsDataSource
import com.joshnark.data_layer.repositories.GifsRepositoryImpl
import com.joshnark.data_layer.repositories.TagsRepositoryImpl
import com.joshnark.domain_layer.repositories.GifsRepository
import com.joshnark.domain_layer.repositories.TagsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesDependenciesModule {

    @Provides
    @Singleton
    fun provideGifsRepository(gifsDataSource: GifsDataSource): GifsRepository {
        return GifsRepositoryImpl(gifsDataSource)
    }

    @Provides
    @Singleton
    fun provideTagsRepository(tagsDataSource: TagsDataSource): TagsRepository {
        return TagsRepositoryImpl(tagsDataSource)
    }
}