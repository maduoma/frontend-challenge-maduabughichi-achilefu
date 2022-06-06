package com.dodemy.frontendchallengemaduabughichiachilefu.data

import com.dodemy.frontendchallengemaduabughichiachilefu.model.BaseCountryResponse
import com.dodemy.frontendchallengemaduabughichiachilefu.model.BaseHolidayResponse
import com.dodemy.frontendchallengemaduabughichiachilefu.model.HolidayRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HolidayService {

    @GET("Countries")
    suspend fun getCountries(): BaseCountryResponse

    @POST("List")
    suspend fun getHolidays(@Body params: HolidayRequest): BaseHolidayResponse
}
