package com.joshnark.framework_layer.networking

import android.content.Context
import com.joshnark.framework_layer.R
import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor(private val context: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter(API_KEY, context.getString(R.string.api_secret)).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        private const val API_KEY = "api_key"
    }

}