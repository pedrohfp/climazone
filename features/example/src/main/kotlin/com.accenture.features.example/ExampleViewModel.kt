package com.accenture.features.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accenture.archref.core.network.MutableLiveResource
import com.accenture.archref.core.network.getLiveData
import com.accenture.archref.core.network.launchResource
import com.accenture.features.example.data.ExampleRepository
import com.accenture.features.example.data.SomethingDTO

internal class ExampleViewModel(private val repository: ExampleRepository): ViewModel() {

    private val _somethingLiveData = MutableLiveResource<SomethingDTO>()
    val somethingLiveData by getLiveData(_somethingLiveData)

    fun sendSomething(something: String) {
        viewModelScope.launchResource(_somethingLiveData, {
            repository.sendSomething(something)
        })
    }
}