package com.dodemy.frontendchallengemaduabughichiachilefu.di

import com.dodemy.frontendchallengemaduabughichiachilefu.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel { HomeViewModel(repository = get()) }
}
