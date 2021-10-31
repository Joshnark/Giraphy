package com.joshnark.framework_layer.services

import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Tag
import com.joshnark.domain_layer.models.giphy_api_responses.NetworkResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TagsServices {

    @GET(TRENDING_TAGS_URL)
    suspend fun getTrendingTags(): NetworkResult<List<Tag>>

    @GET("$SEARCH_TAGS_URL/{keyword}")
    suspend fun getSearchRelatedTags(@Path("keyword") keyword: String): NetworkResult<List<Tag>>

    @GET(CATEGORIES_URL)
    suspend fun getCategories(): NetworkResult<List<Tag>>

    @GET(AUTOCOMPLETE_URL)
    suspend fun getAutoCompletedKeyword(
        @Query("q") keyword: String
    ): NetworkResult<List<Tag>>

    companion object {
        private const val TRENDING_TAGS_URL = "trending/searches"
        private const val SEARCH_TAGS_URL = "tags/related"
        private const val CATEGORIES_URL = "gifs/categories"
        private const val AUTOCOMPLETE_URL = "gifs/search/tags"
    }
}