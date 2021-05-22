package com.goddoro.junction.di

import com.goddoro.junction.network.repository.DriverRepository
import com.goddoro.junction.network.repository.NaverRepository
import com.goddoro.junction.network.repository.TestRepository
import com.goddoro.junction.network.repositoryImpl.DriverRepositoryImpl
import com.goddoro.junction.network.repositoryImpl.NaverRepositoryImpl
import com.goddoro.junction.network.repositoryImpl.TestRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module


/**
 * created By DORO 5/15/21
 */

val repositoryModule = module {

    single { TestRepositoryImpl( get(),get()) } bind TestRepository::class

    single { DriverRepositoryImpl(get()) } bind DriverRepository::class

    single { NaverRepositoryImpl(get()) } bind NaverRepository::class

}