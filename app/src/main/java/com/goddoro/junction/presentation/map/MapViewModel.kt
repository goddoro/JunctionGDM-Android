package com.goddoro.junction.presentation.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goddoro.junction.extensions.Once


/**
 * created By DORO 5/21/21
 */

class MapViewModel : ViewModel() {

    val clickMyLocation : MutableLiveData<Once<Unit>> = MutableLiveData()

    fun onClickMyLocation() {
        clickMyLocation.value = Once(Unit)
    }


}