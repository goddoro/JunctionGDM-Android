package com.goddoro.junction

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goddoro.junction.extensions.Once


/**
 * created By DORO 5/15/21
 */

class MainViewModel : ViewModel() {

    val clickTestActivity : MutableLiveData<Once<Unit>> = MutableLiveData()






    fun onClickTestActivity() {

        clickTestActivity.value = Once(Unit)
    }


}