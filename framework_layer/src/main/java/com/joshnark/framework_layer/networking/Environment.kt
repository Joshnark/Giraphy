package com.joshnark.framework_layer.networking

import android.net.Uri

/**
 * I usually use this singleton in bigger projects with different environments like production, development, etc..
 * and, in consequence, different product flavors to easy change between servers depending in the build variant chosen.
 * Just see how easy I could add a conditional structure in getApiEndpoint to configure everything as I say
 * */
object Environment {

    private const val PROTOCOL = "https"

    private const val PRODUCTION_API_URL = "api.giphy.com"

    private const val API_VERSION = "v1"

    internal fun getApiEndpoint(): String {
        return Uri.Builder()
            .scheme(PROTOCOL)
            .authority(PRODUCTION_API_URL)
            .appendPath(API_VERSION)
            .build()
            .toString()
            .plus("/")
    }

}