package com.goddoro.junction.network.repositoryImpl

import com.goddoro.junction.network.api.DriverAPI
import com.goddoro.junction.network.api.unWrapData
import com.goddoro.junction.network.model.Driver
import com.goddoro.junction.network.repository.DriverRepository
import io.reactivex.Single


/**
 * created By DORO 5/22/21
 */

class DriverRepositoryImpl ( val api : DriverAPI) : DriverRepository {


    override fun listDrivers(): Single<List<Driver>> {


        return api.listDrivers().unWrapData().map{it.drivers}
    }
}