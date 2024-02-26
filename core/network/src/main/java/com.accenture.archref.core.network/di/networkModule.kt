package com.accenture.archref.core.network.di

import com.accenture.archref.core.network.ApiKeyInterceptor
import com.accenture.archref.core.network.BuildConfig
import com.accenture.archref.core.network.CountryInterceptor
import com.accenture.archref.core.network.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    factory {

        OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor().getInterceptor())
            .addInterceptor(ApiKeyInterceptor().getInterceptor())
            .addInterceptor(CountryInterceptor().getInterceptor())
            .build()
    }

    factory {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory()) // For Kotlin data classes
            .build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BuildConfig.baseUrl)
            .client(get())
            .build()
    }
}
