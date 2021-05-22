package com.goddoro.junction.di

import android.util.Log
import com.goddoro.junction.CommonConst.NAVER_CLIENT_ID
import com.goddoro.junction.CommonConst.NAVER_SECRET_ID
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * created By DORO 5/15/21
 */

private const val CONNECT_TIMEOUT = 60L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L

val baseUrl = "http://192.168.0.184:18050"

val naverUrl = "https://naveropenapi.apigw.ntruss.com/"

val networkModule = module {

    single {

        GsonBuilder().create()

    }

    single {
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(get<Interceptor>())
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }


    single {
        Interceptor { chain ->

            chain.proceed(chain.request().newBuilder().apply {

            }.build())
        }
    }

    single( named("NAVER_INTERCEPTOR") ){
        Interceptor { chain ->

            Log.d(
                "NAVER Network Module",
                "========== [ Network Module : Header Intercepter ] ==========="
            )
            chain.proceed(chain.request().newBuilder().apply {
                header("X-NCP-APIGW-API-KEY", NAVER_SECRET_ID)
                addHeader("X-NCP-APIGW-API-KEY-ID", NAVER_CLIENT_ID)
            }.build())
        }

    }


    single ( named("NAVER_OKHTTP") ){
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(get<Interceptor>(named("NAVER_INTERCEPTOR")))
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()

    }



    single(named("NAVER")) {
        Retrofit.Builder()
            .baseUrl(naverUrl)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get(named("NAVER_OKHTTP")))
            .build()
    }

}