package com.pagonxt.archref.features.example.di

import com.pagonxt.archref.features.example.ExampleViewModel
import com.pagonxt.archref.features.example.data.ExampleApi
import com.pagonxt.archref.features.example.data.ExampleRepository
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
