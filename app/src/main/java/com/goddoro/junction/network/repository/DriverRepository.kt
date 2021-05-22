package com.goddoro.junction.network.repository

import com.goddoro.junction.network.model.Driver
import com.goddoro.junction.network.model.DriverLocation
import io.reactivex.Single


/**
 * created By DORO 5/22/21
 */

interface DriverRepository {

    fun listDrivers() : Single<List<Driver>>

    fun getDriverLocation (
        id : Int
    ) : Single<DriverLocation>


}