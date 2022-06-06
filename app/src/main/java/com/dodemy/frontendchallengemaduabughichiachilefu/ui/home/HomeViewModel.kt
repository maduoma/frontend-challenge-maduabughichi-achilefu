package com.dodemy.frontendchallengemaduabughichiachilefu.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dodemy.frontendchallengemaduabughichiachilefu.model.Country
import com.dodemy.frontendchallengemaduabughichiachilefu.repository.HolidayRepository
import com.dodemy.frontendchallengemaduabughichiachilefu.util.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(private val repository: HolidayRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeStates>(HomeStates.Loading)
    val uiState: StateFlow<HomeStates> = _uiState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.value = HomeStates.Error(throwable.handleThrowable())
        Timber.e(throwable)
    }

    init {
        getCountries()
    }

    fun getCountries() {
        _uiState.value = HomeStates.Loading
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = HomeStates.Success(countries = repository.getCountries().countries)
        }
    }
}

sealed class HomeStates {
    object Loading: HomeStates()
    data class Error(val errorMessage: String): HomeStates()
    data class Success(val countries: List<Country>): HomeStates()
}
