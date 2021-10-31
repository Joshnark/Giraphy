package com.joshnark.framework_layer.services

import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.giphy_api_responses.NetworkResult
import retrofit2.http.GET
import retrofit2.http.Query

interface GifsServices {

    @GET(TRENDING_GIFS_URL)
    suspend fun getTrendingGifs(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): NetworkResult<List<Gif>>

    @GET(SEARCH_GIFS_URL)
    suspend fun searchGifs(
        @Query("q") keyword: String
    ): NetworkResult<List<Gif>>

    @GET(RANDOM_GIF_URL)
    suspend fun getRandomGif(): NetworkResult<Gif>

    companion object {
        private const val TRENDING_GIFS_URL = "gifs/trending"
        private const val SEARCH_GIFS_URL = "gifs/search"
        private const val RANDOM_GIF_URL = "gifs/random"
    }

}