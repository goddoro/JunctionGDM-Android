package com.goddoro.junction.presentation.toTaxi

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goddoro.junction.R
import com.goddoro.junction.extensions.rxRepeatTimer
import com.goddoro.junction.network.model.InDrivingText

class ToTaxiViewModel : ViewModel() {

    val toTaxiText: MutableLiveData<List<InDrivingText>> = MutableLiveData(listOf())

    var position = 0

    val textList: List<InDrivingText> = listOf(
        InDrivingText(
            0,
            "10m",
            "Go Straight 10m for about 10 second",
            R.drawable.ic_up_on,
            R.drawable.ic_up_off,
            ObservableInt(0)
        ),
        InDrivingText(
            1,
            "5m",
            "Turn right at the corner and go straight 5m",
            R.drawable.ic_right_on,
            R.drawable.ic_right_off,
            ObservableInt(0)
        ),
        InDrivingText(
            2,
            "Arrived",
            "There is a taxi right in front of you",
            R.drawable.ic_right_on,
            R.drawable.ic_right_off,
            ObservableInt(0)
        ),
        InDrivingText(
            3,
            "Arrived",
            "There is a taxi right in front of you",
            R.drawable.ic_right_on,
            R.drawable.ic_right_off,
            ObservableInt(0)
        ),
        InDrivingText(
            4,
            "Arrived",
            "There is a taxi right in front of you",
            R.drawable.ic_arrived_on,
            R.drawable.ic_arrived_on,
            ObservableInt(0)
        )
    )

    init {
        onAddText()
    }

    private fun onAddText() {


    }
}