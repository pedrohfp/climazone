package com.pagonxt.archref.features.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pagonxt.archref.core.network.MutableLiveResource
import com.pagonxt.archref.core.network.getLiveData
import com.pagonxt.archref.core.network.launchResource
import com.pagonxt.archref.features.example.data.ExampleRepository
import com.pagonxt.archref.features.example.data.SomethingDTO

internal class ExampleViewModel(private val repository: ExampleRepository): ViewModel() {

    private val _somethingLiveData = MutableLiveResource<SomethingDTO>()
    val somethingLiveData by getLiveData(_somethingLiveData)

    fun sendSomething(something: String) {
        viewModelScope.launchResource(_somethingLiveData, {
            repository.sendSomething(something)
        })
    }
}