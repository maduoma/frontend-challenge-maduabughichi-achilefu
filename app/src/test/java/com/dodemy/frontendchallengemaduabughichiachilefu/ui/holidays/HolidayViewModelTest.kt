package com.dodemy.frontendchallengemaduabughichiachilefu.ui.holidays

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.dodemy.frontendchallengemaduabughichiachilefu.country
import com.dodemy.frontendchallengemaduabughichiachilefu.holidayRequest
import com.dodemy.frontendchallengemaduabughichiachilefu.holidayResponse
import com.dodemy.frontendchallengemaduabughichiachilefu.model.BaseHolidayResponse
import com.dodemy.frontendchallengemaduabughichiachilefu.repository.HolidayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HolidayViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(context = testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun getHolidaysTest() = testScope.runTest {
        val mockRepo = mock<HolidayRepository> {
            onBlocking { getHolidays(params = holidayRequest) } doReturn BaseHolidayResponse(
                holidays = holidayResponse
            )
        }

        val viewModel = HolidayViewModel(repository = mockRepo, country = country)
        viewModel.getHolidays()
        val result = viewModel.uiState.value
        if (result is HolidayStates.Success) {
            assert(result.holidays.isNotEmpty())
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
