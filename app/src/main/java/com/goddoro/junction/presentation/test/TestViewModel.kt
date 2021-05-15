package com.goddoro.junction.presentation.test

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goddoro.junction.extensions.Once
import com.goddoro.junction.network.repository.TestRepository
import kotlinx.coroutines.launch


/**
 * created By DORO 5/15/21
 */

class TestViewModel (
    private val testRepository: TestRepository
): ViewModel() {

    val isProcessing : MutableLiveData<Boolean> = MutableLiveData()

    val errorInvoked  : MutableLiveData<Once<Throwable>> = MutableLiveData()

    val result : MutableLiveData<String> = MutableLiveData()


    init {


    }


    fun analyzeImage( imageUri : Uri ) {

        isProcessing.value = true

        viewModelScope.launch {

            kotlin.runCatching {
                testRepository.getMnistAnalysis(imageUri)
            }.onSuccess {
                isProcessing.value = false
                result.value = it.success.toString()
            }.onFailure {
                isProcessing.value = false
                errorInvoked.value = Once(it)
            }
        }
    }
}


