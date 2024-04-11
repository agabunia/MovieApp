package com.example.midterm_project.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midterm_project.domain.useCases.datastore.ReadDatastoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val readDatastoreUseCase: ReadDatastoreUseCase
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<SplashEvent>()
    val uiEvent: SharedFlow<SplashEvent> get() = _uiEvent


    init {
        readSession()
    }

    private fun readSession() {
        viewModelScope.launch {
            readDatastoreUseCase().collect{
                if (it.isEmpty()) {
                    _uiEvent.emit(SplashEvent.NavigateToLogin)
                } else {
                    _uiEvent.emit(SplashEvent.NavigateToMain)
                }
            }
        }
    }

    sealed interface SplashEvent {
        object NavigateToLogin : SplashEvent
        object NavigateToMain : SplashEvent
    }
}