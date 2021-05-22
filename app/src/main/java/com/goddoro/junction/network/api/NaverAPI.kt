package com.goddoro.junction.network.api

import android.os.Parcelable
import com.goddoro.junction.network.model.Address
import com.goddoro.junction.network.model.Meta
import com.goddoro.junction.network.model.Result
import com.goddoro.junction.network.model.Status
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NaverAPI {


    @GET("/map-reversegeocode/v2/gc")
    suspend fun getAddress(
        @QueryMap parameters: HashMap<String, Any>
    ) : AddressResponse

    @GET("/map-geocode/v2/geocode")
    suspend fun getLocation(
        @QueryMap parameters : HashMap<String,Any>
    ) : LocationResponse


}


@Parcelize
data class AddressResponse(
    @SerializedName("status")
    val status : Status,

    @SerializedName("results")
    val results : List<Result>
) : Parcelable

@Parcelize
data class LocationResponse(

    @SerializedName("status")
    val status : String,

    @SerializedName("meta")
    val meta : Meta,

    @SerializedName("addresses")
    val addresses : List<Address>,

    @SerializedName("errorMessage")
    val errorMessage : String

) : Parcelable