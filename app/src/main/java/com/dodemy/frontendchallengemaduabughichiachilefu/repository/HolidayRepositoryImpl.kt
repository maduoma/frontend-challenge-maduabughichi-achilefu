package com.dodemy.frontendchallengemaduabughichiachilefu.repository

import com.dodemy.frontendchallengemaduabughichiachilefu.data.HolidayService
import com.dodemy.frontendchallengemaduabughichiachilefu.model.BaseCountryResponse
import com.dodemy.frontendchallengemaduabughichiachilefu.model.BaseHolidayResponse
import com.dodemy.frontendchallengemaduabughichiachilefu.model.HolidayRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class HolidayRepositoryImpl(
    private val api: HolidayService,
    private val dispatcher: CoroutineDispatcher
) : HolidayRepository {
    override suspend fun getCountries(): BaseCountryResponse {
        return withContext(dispatcher) { api.getCountries() }
    }

    override suspend fun getHolidays(params: HolidayRequest): BaseHolidayResponse {
        return withContext(dispatcher) { api.getHolidays(params) }
    }
}
