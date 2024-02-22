package com.accenture.archref.core.network

import com.accenture.archref.BuildConfig
import okhttp3.Interceptor

class ApiKeyInterceptor {
    fun getInterceptor() = Interceptor { chain ->
        val originalRequest = chain.request()
        val modifiedUrl = originalRequest.url.newBuilder()
            .addQueryParameter("appId", BuildConfig.apiKey)
            .build()
        val modifiedRequest = originalRequest.newBuilder()
            .url(modifiedUrl)
            .build()
        chain.proceed(modifiedRequest)
    }
}
