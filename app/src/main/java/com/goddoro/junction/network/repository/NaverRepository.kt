package com.goddoro.junction.network.repository

import com.goddoro.junction.network.api.AddressResponse
import com.goddoro.junction.network.api.LocationResponse

interface NaverRepository {


    suspend fun getAddressFromLocation (
        latitude : Double,
        longitude : Double
    ) : AddressResponse


    suspend fun getLocationFromAddress(
        address : String
    ) : LocationResponse
}