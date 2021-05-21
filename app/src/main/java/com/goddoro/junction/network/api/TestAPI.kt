package com.goddoro.junction.network.api

import com.goddoro.junction.network.model.Test
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap


/**
 * created By DORO 5/15/21
 */

interface TestAPI {

    @POST("/mnist")
    @Multipart
    suspend fun getMnistAnalysis (
        @Part imageFile: MultipartBody.Part
    ) : Test

}

