package com.goddoro.junction.presentation.indriving

import android.util.Log
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goddoro.junction.R
import com.goddoro.junction.extensions.rxRepeatTimer
import com.goddoro.junction.network.model.InDrivingText

class InDrivingViewModel : ViewModel() {

    val inDrivingTextList : MutableLiveData<List<InDrivingText>> = MutableLiveData()

    var textList : List<InDrivingText> = listOf()


    val second: List<Int> = listOf(2, 2, 2, 2, 2, 3,0)

    var position = 0

    val curTime : MutableLiveData<Int> = MutableLiveData(-1)

    val isRed : MutableLiveData<Boolean> = MutableLiveData()



    init {

        textList = listOf(
            InDrivingText(0,"100m","Go Straight for 100m from Jongno 1-gil", R.drawable.ic_straight_on, R.drawable.ic_straight_off, ObservableInt(0)),
            InDrivingText(1,"Lane Change","Move from lane 2 to lane 1 for a left turn", R.drawable.ic_lane_change_on, R.drawable.ic_lane_change_off, ObservableInt(0)),
            InDrivingText(2,"Caution","Prepare for a sudden stop in 2 seconds", R.drawable.ic_caution_on, R.drawable.ic_caution_off,ObservableInt(0), R.color.colorRed),
            InDrivingText(3,"Delay","Arrival time is delayed by 10 minutes due to a collision",R.drawable.ic_delay_on,R.drawable.ic_delay_off,
                ObservableInt(0)
            ),
            InDrivingText(4,"Arrived","The taxi has arrived at the destination",R.drawable.ic_arrived_on,R.drawable.ic_arrived_off,ObservableInt(0)),
            InDrivingText(5,"Wait","The car is approaching. Get off after 30 seconds", R.drawable.ic_wait_on,R.drawable.ic_wait_on,ObservableInt(0),R.color.colorRed)
        )

    }

}