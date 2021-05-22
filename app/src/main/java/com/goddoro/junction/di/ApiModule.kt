package com.goddoro.junction.di

import com.goddoro.junction.network.api.DriverAPI
import com.goddoro.junction.network.api.NaverAPI
import com.goddoro.junction.network.api.TestAPI
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.http.*


/**
 * created By DORO 5/15/21
 */

val apiModule = module {



    single { get<Retrofit>().create(TestAPI::class.java) }
    single { get<Retrofit>().create(DriverAPI::class.java)}
    single { get<Retrofit>(named("NAVER")).create(NaverAPI::class.java)}

}