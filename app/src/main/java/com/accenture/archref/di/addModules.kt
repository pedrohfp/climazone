package com.accenture.archref.di

import com.accenture.archref.core.network.di.networkModule
import com.accenture.archref.features.example.di.exampleModule

val addModules = listOf(
    networkModule,
    exampleModule
)
