package com.accenture.features.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accenture.archref.core.network.MutableLiveResource
import com.accenture.archref.core.network.getLiveData
import com.accenture.archref.core.network.launchResource
import com.accenture.features.example.data.ExampleRepository
import com.accenture.features.example.data.TopHeadlineDTO

internal class ExampleViewModel(private val repository: ExampleRepository): ViewModel() {

    private val _somethingLiveData = MutableLiveResource<TopHeadlineDTO>()
    val somethingLiveData by getLiveData(_somethingLiveData)

    fun sendSomething() {
        viewModelScope.launchResource(_somethingLiveData, {
            repository.loadHeadlines()
        })
    }
}