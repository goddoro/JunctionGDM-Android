package com.goddoro.junction.network.api

import android.os.Parcelable
import com.goddoro.junction.network.model.Driver
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize
import retrofit2.http.GET


/**
 * created By DORO 5/22/21
 */

interface DriverAPI {

    @GET("/drivers")
    fun listDrivers() : Single<ApiResponse<DriverListResponse>>
}


@Parcelize
data class DriverListResponse(
    @SerializedName("drivers")
    val drivers : List<Driver>
) : Parcelable