package com.goddoro.junction.presentation.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goddoro.junction.extensions.Once
import com.goddoro.junction.network.repository.NaverRepository
import kotlinx.coroutines.launch


/**
 * created By DORO 5/21/21
 */

class MapViewModel(
    private val naverRepository: NaverRepository
) : ViewModel() {

    private val TAG = MapViewModel::class.java.simpleName


    val currentAddress: MutableLiveData<String> = MutableLiveData()

    val isValidAddress: MutableLiveData<Boolean> = MutableLiveData()

    val clickMyLocation: MutableLiveData<Once<Unit>> = MutableLiveData()

    val errorInvoked: MutableLiveData<Once<Throwable>> = MutableLiveData()


    fun onClickMyLocation() {
        clickMyLocation.value = Once(Unit)
    }


    fun onCameraChange(latitude: Double, longitude: Double) {

        Log.d(TAG, "Let'sgo change")

        viewModelScope.launch {
            kotlin.runCatching {
                naverRepository.getAddressFromLocation(latitude, longitude)
            }.onSuccess {

                if (it.status.name == "ok") {
                    currentAddress.value = it.results[0].let {
                        it
                        it.region.area1.name + " " + it.region.area2.name + " " + it.region.area3.name + " " + it.region.area4.name + " " + it.land.name + " " + it.land.number1

                    }
                    isValidAddress.value = true
                } else {
                    isValidAddress.value = false
                    currentAddress.value = "좀 더 줌을 땡겨서 정확한 주소를 찾아주세요!"
                }

                Log.d("NAVER", currentAddress.value.toString())

            }.onFailure {
                errorInvoked.value = Once(it)
            }
        }
    }


}