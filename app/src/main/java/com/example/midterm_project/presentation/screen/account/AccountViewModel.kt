package com.example.midterm_project.presentation.screen.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midterm_project.domain.useCases.datastore.ClearDatastoreUseCase
import com.example.midterm_project.domain.useCases.datastore.ReadDatastoreUseCase
import com.example.midterm_project.presentation.event.account.AccountEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val clearDatastoreUseCase: ClearDatastoreUseCase,
    private val readDatastoreUseCase: ReadDatastoreUseCase
) : ViewModel() {

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> get() = _token

    private val _uiEvent = MutableSharedFlow<AccountUiEvent>()
    val uiEvent: SharedFlow<AccountUiEvent> get() = _uiEvent

    fun onEvent(event: AccountEvent) {
        when (event) {
            is AccountEvent.Logout -> logout()
            is AccountEvent.NavigateToMain -> navigateToMain()
        }
    }

    init {
        readToken()
    }

    private fun readToken() {
        viewModelScope.launch {
            readDatastoreUseCase().collect{
                _token.value = it
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            clearDatastoreUseCase()
            _uiEvent.emit(AccountUiEvent.NavigateToLogin)
        }
    }

    private fun navigateToMain() {
        viewModelScope.launch {
            _uiEvent.emit(AccountUiEvent.NavigateToMain)
        }
    }

    sealed interface AccountUiEvent {
        object NavigateToLogin : AccountUiEvent
        object NavigateToMain: AccountUiEvent
    }
}