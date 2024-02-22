package com.accenture.archref.core.network

import okhttp3.Interceptor

class ApiKeyInterceptor {
    fun getInterceptor() = Interceptor { chain ->
        val originalRequest = chain.request()
        val modifiedUrl = originalRequest.url.newBuilder()
            .addQueryParameter("appId", "teste")
            .build()
        val modifiedRequest = originalRequest.newBuilder()
            .url(modifiedUrl)
            .build()
        chain.proceed(modifiedRequest)
    }
}
