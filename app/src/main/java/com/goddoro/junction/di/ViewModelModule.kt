package com.goddoro.junction.di

import com.goddoro.junction.MainViewModel
import com.goddoro.junction.presentation.feed.FeedViewModel
import com.goddoro.junction.presentation.map.MapViewModel
import com.goddoro.junction.presentation.test.TestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * created By DORO 5/15/21
 */

val viewModelModule = module {

    viewModel { MainViewModel() }
    viewModel { TestViewModel(get()) }
    viewModel { FeedViewModel()}
    viewModel { MapViewModel()}

}