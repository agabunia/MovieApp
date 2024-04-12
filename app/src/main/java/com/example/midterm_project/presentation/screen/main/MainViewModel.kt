package com.example.midterm_project.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.domain.repository.main.MainRepository
import com.example.midterm_project.domain.useCases.main.GetMainMoviesUseCase
import com.example.midterm_project.presentation.event.main.MainEvent
import com.example.midterm_project.presentation.mapper.main.toPresenter
import com.example.midterm_project.presentation.model.main.MoviesFragmentList
import com.example.midterm_project.presentation.state.main.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMainMoviesUseCase: GetMainMoviesUseCase
) : ViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState: SharedFlow<MainState> = _mainState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<MainUIEvent>()
    val uiEvent: SharedFlow<MainUIEvent> get() = _uiEvent

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.FetchMovies -> fetchMovies(event.filters)
            is MainEvent.MovieList -> navigateToMovieList()
            is MainEvent.Account -> navigateToAccount()
            is MainEvent.NavigateToDetailed -> navigateToDetailed(id = event.id)
        }
    }

    private val movieListsMap = mutableMapOf<String, List<MoviesFragmentList>>()

    private fun fetchMovies(filters: List<Pair<String, String>>) {
        viewModelScope.launch {
            for ((fragmentName, fragmentJsonName) in filters) {
                getMainMoviesUseCase(fragmentJsonName = fragmentJsonName).collect {
                    when (it) {
                        is Resource.Success -> {
                            val newList = listOf(
                                MoviesFragmentList(
                                    fragmentName,
                                    fragmentJsonName,
                                    it.data.toPresenter()
                                )
                            )
                            movieListsMap[fragmentName] = newList
                            _mainState.update { currentState ->
                                currentState.copy(
                                    movieFragmentList = movieListsMap.values.flatten()
                                )
                            }
                        }

                        is Resource.Error -> {
                            errorMessage(message = it.errorMessage)
                        }

                        is Resource.Loading -> {
                            _mainState.update { currentState -> currentState.copy(isLoading = it.loading) }
                        }
                    }
                }
            }
        }
    }


    private fun errorMessage(message: String?) {
        _mainState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    private fun navigateToMovieList() {
        viewModelScope.launch {
            _uiEvent.emit(MainUIEvent.NavigateToMovieList)
        }
    }

    private fun navigateToAccount() {
        viewModelScope.launch {
            _uiEvent.emit(MainUIEvent.NavigateToAccount)
        }
    }

    private fun navigateToDetailed(id: Int) {
        viewModelScope.launch {
            _uiEvent.emit(MainUIEvent.NavigateToDetailed(id = id))
        }
    }

    sealed interface MainUIEvent {
        object NavigateToMovieList : MainUIEvent
        object NavigateToAccount : MainUIEvent
        data class NavigateToDetailed(val id: Int): MainUIEvent
    }
}
