package com.goddoro.junction.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DriverLocation(
    @SerializedName("latitude")
    val latitude : Float,

    @SerializedName("longitude")
    val longitude : Float,

    @SerializedName("driver")
    val driver : Driver

) : Parcelable