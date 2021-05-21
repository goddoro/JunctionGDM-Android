package com.goddoro.junction.network.repository

import com.goddoro.junction.network.model.Driver
import io.reactivex.Single


/**
 * created By DORO 5/22/21
 */

interface DriverRepository {

    fun listDrivers() : Single<List<Driver>>


}