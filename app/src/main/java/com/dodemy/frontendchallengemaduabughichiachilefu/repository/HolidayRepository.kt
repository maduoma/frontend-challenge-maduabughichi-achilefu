package com.dodemy.frontendchallengemaduabughichiachilefu.repository

import com.dodemy.frontendchallengemaduabughichiachilefu.model.BaseCountryResponse
import com.dodemy.frontendchallengemaduabughichiachilefu.model.BaseHolidayResponse
import com.dodemy.frontendchallengemaduabughichiachilefu.model.HolidayRequest

interface HolidayRepository {
    suspend fun getCountries(): BaseCountryResponse
    suspend fun getHolidays(params: HolidayRequest): BaseHolidayResponse
}
