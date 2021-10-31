package com.joshnark.presentation_layer.di

import com.joshnark.domain_layer.repositories.GifsRepository
import com.joshnark.domain_layer.repositories.TagsRepository
import com.joshnark.presentation_layer.CategoriesViewModel
import com.joshnark.presentation_layer.viewmodels.GifMainViewModel
import com.joshnark.use_cases_layer.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
class GifDependenciesModule {

    @Provides
    fun provideGifsViewModel(gifsRepository: GifsRepository): GifMainViewModel {
        return GifMainViewModel(
            GetTrendingGifsUseCase(gifsRepository),
            AddGifToFavoritesUseCase(gifsRepository),
            GetFavoritedGifsUseCase(gifsRepository),
            SearchGifsUseCase(gifsRepository),
        )
    }

    @Provides
    fun provideCategoriesViewModel(tagsRepository: TagsRepository): CategoriesViewModel {
        return CategoriesViewModel(
            GetCategoriesUseCase(tagsRepository)
        )
    }

}