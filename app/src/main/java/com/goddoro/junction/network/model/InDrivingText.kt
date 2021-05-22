package com.goddoro.junction.network.model

import androidx.databinding.ObservableInt

data class InDrivingText (

    val id : Int,
    val meter : String,

    val description : String,

    val onIcon : Int,

    val offIcon : Int,

    val position : ObservableInt,

    val background : Int? = 0

)