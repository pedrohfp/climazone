package com.accenture.archref.core.network

import okhttp3.Interceptor

class CountryInterceptor {
    fun getInterceptor() = Interceptor { chain ->
        val originalRequest = chain.request()
        val modifiedUrl = originalRequest.url.newBuilder()
            .addQueryParameter("country", "br")
            .build()
        val modifiedRequest = originalRequest.newBuilder()
            .url(modifiedUrl)
            .build()
        chain.proceed(modifiedRequest)
    }
}
