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

    val imageUri : Uri? = null

    val onSuccess : MutableLiveData<Once<Unit>> = MutableLiveData()
    val errorInvoked  : MutableLiveData<Once<Throwable>> = MutableLiveData()


    init {


    }


    fun analyzeImage() {

        viewModelScope.launch {


            kotlin.runCatching {
                testRepository.getMnistAnalysis(imageUri!!)
            }.onSuccess {
                onSuccess.value = Once(Unit)
            }.onFailure {
                errorInvoked.value = Once(it)
            }
        }
    }
}


