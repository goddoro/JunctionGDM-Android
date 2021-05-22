package com.goddoro.junction.di

import com.goddoro.junction.MainViewModel
import com.goddoro.junction.presentation.apply.ApplyViewModel
import com.goddoro.junction.presentation.feed.FeedViewModel
import com.goddoro.junction.presentation.feed.detail.DriverDetailViewModel
import com.goddoro.junction.presentation.intro.IntroViewModel
import com.goddoro.junction.presentation.map.MapViewModel
import com.goddoro.junction.presentation.profile.ProfileViewModel
import com.goddoro.junction.presentation.review.ReviewViewModel
import com.goddoro.junction.presentation.test.TestViewModel
import com.goddoro.junction.util.component.BottomSheetViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * created By DORO 5/15/21
 */

val viewModelModule = module {

    viewModel { MainViewModel() }
    viewModel { TestViewModel(get()) }
    viewModel { FeedViewModel(get())}
    viewModel { MapViewModel(get())}
    viewModel { DriverDetailViewModel() }
    viewModel { BottomSheetViewModel() }
    viewModel { ProfileViewModel()}
    viewModel { ApplyViewModel()}
    viewModel { ReviewViewModel() }
    viewModel { IntroViewModel() }

}