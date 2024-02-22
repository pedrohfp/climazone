package com.pagonxt.archref.features.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pagonxt.archref.core.network.observeOnLoading
import com.pagonxt.archref.core.network.observeOnSuccess
import com.pagonxt.archref.core.network.observeOnError
import com.pagonxt.archref.core.network.setLifecycleOwner
import com.pagonxt.archref.features.example.data.SomethingDTO
import com.pagonxt.archref.features.example.databinding.ActivityExampleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ExampleActivity : AppCompatActivity() {

    private val viewModel: ExampleViewModel by viewModel()

    private lateinit var binding: ActivityExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        binding.btnSend.setOnClickListener {
            viewModel.sendSomething(binding.edtTypeSomething.text.toString())
        }
    }

    private fun setupObservers() {
        viewModel.somethingLiveData
            .setLifecycleOwner(this)
            .observeOnSuccess(::onSuccess)
            .observeOnLoading(::onLoading)
            .observeOnError(::onError)
    }

    private fun onSuccess(somethingDTO: SomethingDTO) {
        Toast.makeText(this, somethingDTO.first, Toast.LENGTH_LONG).show()
    }

    private fun onLoading() {
        //Show loading view
    }

    private fun onError(errorCode: Int) {
        //Show error view
        Toast.makeText(this, errorCode.toString(), Toast.LENGTH_LONG).show()
    }
}