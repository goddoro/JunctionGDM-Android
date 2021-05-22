package com.goddoro.junction.presentation.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goddoro.junction.extensions.Once
import com.goddoro.junction.extensions.addSchedulers
import com.goddoro.junction.extensions.disposedBy
import com.goddoro.junction.network.model.Driver
import com.goddoro.junction.network.repository.DriverRepository
import io.reactivex.disposables.CompositeDisposable


/**
 * created By DORO 5/21/21
 */

class FeedViewModel (
    val driverRepository: DriverRepository
) : ViewModel() {

    val drivers : MutableLiveData<List<Driver>> = MutableLiveData()

    val speechText : MutableLiveData<String> = MutableLiveData()


    val compositeDisposable = CompositeDisposable()

    val clickSpeech : MutableLiveData<Once<Unit>> = MutableLiveData()
    val errorInvoked : MutableLiveData<Once<Throwable>> = MutableLiveData()


    init {

        listDrivers()
    }

    fun listDrivers() {

        driverRepository.listDrivers()
            .addSchedulers()
            .subscribe({
                drivers.value = it
            },{
                errorInvoked.value = Once(it)
            }).disposedBy(compositeDisposable)
    }

    fun onClickSpeech() {
        clickSpeech.value = Once(Unit)
    }


}