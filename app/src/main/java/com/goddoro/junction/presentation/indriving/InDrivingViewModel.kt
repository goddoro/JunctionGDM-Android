package com.goddoro.junction.presentation.indriving

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goddoro.junction.R
import com.goddoro.junction.extensions.rxRepeatTimer
import com.goddoro.junction.network.model.InDrivingText

class InDrivingViewModel : ViewModel() {

    val inDrivingTextList : MutableLiveData<List<InDrivingText>> = MutableLiveData()

    var textList : List<InDrivingText> = listOf()

    var position = 0

    init {

        textList = listOf(
            InDrivingText(0,"69m","1차선으로 차선 변경하겠습니다", R.drawable.ic_android, R.color.white),
            InDrivingText(1,"69m","현재 일산방향 강변북로에 40분가량 정체가 있습니다", R.drawable.ic_android, R.color.white),
            InDrivingText(2,"69m","주황불을 통과하기 위해 시속 18km가 상승하였습니다", R.drawable.ic_android, R.color.white)
        )

        onAddText()
    }

    private fun onAddText() {

        rxRepeatTimer(3000){
            if ( position < textList.size) {
                inDrivingTextList.value =  listOf(textList[position]) + (inDrivingTextList.value ?: listOf())
                position++
            }
        }

    }
}