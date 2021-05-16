package com.goddoro.junction.di

import com.goddoro.junction.network.repository.TestRepository
import com.goddoro.junction.network.repositoryImpl.TestRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module


/**
 * created By DORO 5/15/21
 */

val repositoryModule = module {

    single { TestRepositoryImpl( get(),get()) } bind TestRepository::class

}