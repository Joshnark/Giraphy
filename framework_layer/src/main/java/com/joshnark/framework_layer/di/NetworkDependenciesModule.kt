package com.joshnark.framework_layer.di

import android.app.Application
import android.content.SharedPreferences
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.joshnark.framework_layer.services.GifsServices
import com.joshnark.framework_layer.services.TagsServices
import com.joshnark.framework_layer.networking.Environment
import com.joshnark.framework_layer.networking.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkDependenciesModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor(application))
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkAdapter(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Environment.getApiEndpoint())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun getGifsService(retrofit: Retrofit): GifsServices = retrofit.create(GifsServices::class.java)

    @Provides
    @Singleton
    fun getTagsService(retrofit: Retrofit): TagsServices = retrofit.create(TagsServices::class.java)

}