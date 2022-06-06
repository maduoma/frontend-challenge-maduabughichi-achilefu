package com.dodemy.frontendchallengemaduabughichiachilefu.di

import com.dodemy.frontendchallengemaduabughichiachilefu.ui.auth.LoginViewModel
//import com.project.countryholiday.ui.auth.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {

    viewModel { LoginViewModel() }
}
