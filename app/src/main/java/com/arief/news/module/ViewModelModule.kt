package com.arief.news.module

import com.arief.news.ui.business.BusinessViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BusinessViewModel(get(), get()) }
}