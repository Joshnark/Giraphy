package com.joshnark.domain_layer.models.giphy_api_responses

data class NetworkResult<T>(
    val data: T,
    val meta: NetworkMetaData
)
