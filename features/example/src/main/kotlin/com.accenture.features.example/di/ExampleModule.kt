package com.accenture.archref.features.example.di

import com.accenture.features.example.ExampleViewModel
import com.accenture.features.example.data.ExampleApi
import com.accenture.features.example.data.ExampleRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val exampleModule = module {
    factory {
        val retrofit: Retrofit = get()
        ExampleRepository(retrofit.create(ExampleApi::class.java))
    }

    viewModel { ExampleViewModel(get()) }
}
