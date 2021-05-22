package com.goddoro.junction.network.api

import android.os.Parcelable
import com.goddoro.junction.network.model.Driver
import com.goddoro.junction.network.model.DriverLocation
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * created By DORO 5/22/21
 */

interface DriverAPI {

    @GET("/drivers")
    fun listDrivers() : Single<ApiResponse<DriverListResponse>>

    @GET("/driver/{id}/location")
    fun getDriverLocation ( @Path("id") id : Int ) : Single<ApiResponse<DriverLocationResponse>>
}


@Parcelize
data class DriverListResponse(
    @SerializedName("drivers")
    val drivers : List<Driver>
) : Parcelable

@Parcelize
data class DriverLocationResponse(

    @SerializedName("driver_location")
    val driverLocation : DriverLocation
) : Parcelable