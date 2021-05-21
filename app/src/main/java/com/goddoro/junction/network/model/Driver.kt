package com.goddoro.junction.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * created By DORO 5/22/21
 */

@Parcelize
data class Driver(
    @SerializedName("id")
    val id : Int,

    @SerializedName("score")
    val score : Int


) : Parcelable