package com.goddoro.junction.network.repositoryImpl

import com.goddoro.junction.extensions.filterValueNotNull
import com.goddoro.junction.network.api.AddressResponse
import com.goddoro.junction.network.api.LocationResponse
import com.goddoro.junction.network.api.NaverAPI
import com.goddoro.junction.network.repository.NaverRepository

class NaverRepositoryImpl ( val naverApi : NaverAPI) : NaverRepository {
    override suspend fun getAddressFromLocation(latitude: Double, longitude: Double): AddressResponse {


        val parameters = hashMapOf(
            "output" to "json",
            "coords" to "$latitude,$longitude",
            "orders" to "roadaddr"
        ).filterValueNotNull()

        return naverApi.getAddress(parameters)
    }

    override suspend fun getLocationFromAddress(address: String): LocationResponse {

        val parameters = hashMapOf(
            "query" to address
        ).filterValueNotNull()

        return naverApi.getLocation(parameters)
    }


}