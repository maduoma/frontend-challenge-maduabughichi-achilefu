package com.dodemy.frontendchallengemaduabughichiachilefu.di

import com.dodemy.frontendchallengemaduabughichiachilefu.model.Country
import com.dodemy.frontendchallengemaduabughichiachilefu.ui.holidays.HolidayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val holidayModule = module {
    viewModel { (country: Country) ->
        HolidayViewModel(repository = get(), country = country)
    }
}
