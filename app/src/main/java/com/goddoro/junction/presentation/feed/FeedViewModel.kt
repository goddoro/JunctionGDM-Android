package com.goddoro.junction.presentation.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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


    val compositeDisposable = CompositeDisposable()


    init {

        //listDrivers()

        drivers.value = listOf(
            Driver(0,0),
            Driver(1,0),
            Driver(2,0)
        )
    }

    fun listDrivers() {

        driverRepository.listDrivers()
            .addSchedulers()
            .subscribe({

            },{

            }).disposedBy(compositeDisposable)
    }


}