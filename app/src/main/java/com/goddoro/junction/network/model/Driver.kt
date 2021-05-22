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

    @SerializedName("rating")
    val score : Float,

    @SerializedName("name")
    val name : String,
    @SerializedName("car_type")
    val carType : String,

    @SerializedName("img_path")
    val profileImgUrl : String,

    @SerializedName("review_score")
    val reviewScore : Float,

    @SerializedName("reviews")
    val reviews : List<String>,

    @SerializedName("speed_score")
    val speedScore : Float,

    @SerializedName("stability_score")
    val stabilityScore : Float,

    @SerializedName("total_score")
    val totalScore : Float,

    @SerializedName("trips")
    val driveCount : Int


) : Parcelable