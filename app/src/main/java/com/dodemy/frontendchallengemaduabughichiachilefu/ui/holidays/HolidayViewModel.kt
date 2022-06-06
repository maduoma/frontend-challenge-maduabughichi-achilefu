package com.dodemy.frontendchallengemaduabughichiachilefu.ui.holidays

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dodemy.frontendchallengemaduabughichiachilefu.model.Country
import com.dodemy.frontendchallengemaduabughichiachilefu.model.Holiday
import com.dodemy.frontendchallengemaduabughichiachilefu.model.HolidayRequest
import com.dodemy.frontendchallengemaduabughichiachilefu.repository.HolidayRepository
import com.dodemy.frontendchallengemaduabughichiachilefu.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class HolidayViewModel(private val repository: HolidayRepository, private val country: Country) :
    ViewModel() {

    private val _uiState = MutableStateFlow<HolidayStates>(HolidayStates.Loading)
    val uiState: StateFlow<HolidayStates> = _uiState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        _uiState.value = HolidayStates.Error(throwable.handleThrowable())
    }

    init {
        getHolidays()
    }

    fun getHolidays() {
        _uiState.value = HolidayStates.Loading
        viewModelScope.launch(exceptionHandler) {
            val holidays = repository.getHolidays(HolidayRequest(countryCode = country.code)).holidays
            _uiState.value = HolidayStates.Success(holidays)
        }
    }
}

sealed class HolidayStates {
    object Loading: HolidayStates()
    data class Error(val error: String): HolidayStates()
    data class Success(val holidays: List<Holiday>): HolidayStates()
}
