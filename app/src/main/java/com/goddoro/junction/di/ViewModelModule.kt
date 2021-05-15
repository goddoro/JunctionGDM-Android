package com.goddoro.junction.di

import com.goddoro.junction.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * created By DORO 5/15/21
 */

val viewModelModule = module {

    viewModel { MainViewModel() }

}