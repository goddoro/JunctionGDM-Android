package com.goddoro.junction.network.api

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


/**
 * created By DORO 5/22/21
 */

@Parcelize
data class ApiResponse<out T>(

    @SerializedName("success")
    @Expose
    val success: Boolean,

    @SerializedName("data")
    @Expose
    val data: @RawValue T?
) : Parcelable

/**
 * 기본적인 ApiResponse 에서 data field 만 얻어내기 위한 헬퍼 메서드
 */
fun <T> Single<ApiResponse<T>>.unWrapData() =
    map {
        when (it.success) {


            true -> it.data!!

            else ->{
                it.data!!
            }
        }
    }
