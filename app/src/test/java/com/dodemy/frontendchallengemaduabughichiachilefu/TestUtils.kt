package com.dodemy.frontendchallengemaduabughichiachilefu

import com.dodemy.frontendchallengemaduabughichiachilefu.model.Country
import com.dodemy.frontendchallengemaduabughichiachilefu.model.Holiday
import com.dodemy.frontendchallengemaduabughichiachilefu.model.HolidayRequest

val countries = listOf(
    Country("BZ", "Brazil"),
    Country("GBP", "United Kingdom")
)

val country = Country("US", "United States of America")

val holidayRequest = HolidayRequest(countryCode = "US")

val holidayResponse = listOf(
    Holiday(
        countryCode = "US",
        date = "2022-12-25",
        localName = "Christmas Day",
        name = "Christmas Day",
        regions = listOf(),
        types = listOf("Public")
    )
)
