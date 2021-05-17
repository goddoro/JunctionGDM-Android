package com.goddoro.junction.di

import com.goddoro.junction.util.AppPreference
import com.goddoro.module.util.MultiPartUtil
import com.goddoro.module.util.UriUtil
import org.koin.dsl.module


/**
 * created By DORO 5/15/21
 */

val utilModule = module{


    single { UriUtil(get()) }
    single { MultiPartUtil(get()) }
    single { AppPreference(get())}
}