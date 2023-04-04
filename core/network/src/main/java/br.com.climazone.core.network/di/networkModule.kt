package br.com.climazone.core.network.di

import br.com.climazone.BuildConfig
import br.com.climazone.core.network.ApiKeyInterceptor
import br.com.climazone.core.network.LocationInterceptor
import br.com.climazone.core.network.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    factory<Interceptor> {
        LoggingInterceptor().getInterceptor()
    }

    factory {
        val interceptor: Interceptor = get()

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(ApiKeyInterceptor().getInterceptor())
            .addInterceptor(LocationInterceptor(androidContext()))
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
