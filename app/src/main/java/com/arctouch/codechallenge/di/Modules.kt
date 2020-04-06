package com.arctouch.codechallenge.di

import com.arctouch.codechallenge.AppExecutors
import com.arctouch.codechallenge.api.retrofit.RetrofitInitializer
import com.arctouch.codechallenge.ui.home.HomeViewModel
import com.arctouch.codechallenge.repository.GenreRepository
import com.arctouch.codechallenge.repository.MovieRepository
import com.arctouch.codechallenge.ui.detail.DetailViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppExecutors() }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

val dataSourceModule = module {
    single { MovieRepository(get(), get()) }
    single { GenreRepository(get()) }

    single { RetrofitInitializer(androidApplication()).tmdbService()}
}

val appModules = arrayListOf(appModule, dataSourceModule)