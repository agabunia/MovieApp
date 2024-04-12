package com.example.midterm_project.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.domain.repository.list.GenresRepository
import com.example.midterm_project.domain.repository.list.MovieFilterRepository
import com.example.midterm_project.domain.useCases.list.GetGenresUseCase
import com.example.midterm_project.domain.useCases.list.MoviesFilterUseCase
import com.example.midterm_project.domain.useCases.list.SearchMoviesUseCase
import com.example.midterm_project.presentation.event.list.ListEvent
import com.example.midterm_project.presentation.mapper.list.toPresenter
import com.example.midterm_project.presentation.state.list.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val moviesFilterUseCase: MoviesFilterUseCase,
    private val filterMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {
    private val _listState = MutableStateFlow(ListState())
    val listState: SharedFlow<ListState> = _listState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<ListUIEvent>()
    val uiEvent: SharedFlow<ListUIEvent> get() = _uiEvent

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.FetchGenres -> fetchGenres()
            is ListEvent.FetchMovies -> fetchMovies(id = event.id)
            is ListEvent.FetchSearchedMovies -> searchMovies(title = event.title)
            is ListEvent.NavigateToDetailed -> navigateToDetailed(id = event.id)
            is ListEvent.NavigateToMain -> navigateToMain()
            is ListEvent.ResetErrorMessage -> errorMessage(message = null)
        }
    }

    private fun fetchGenres() {
        viewModelScope.launch {
            getGenresUseCase().collect {
                when (it) {
                    is Resource.Success -> {

                        _listState.update { currentState ->
                            currentState.copy(genres = it.data.toPresenter().genres)
                        }
                    }

                    is Resource.Error -> errorMessage(message = it.errorMessage)

                    is Resource.Loading -> {
                        _listState.update { currentState ->
                            currentState.copy(isLoading = it.loading)
                        }
                    }
                }
            }
        }
    }

    private fun fetchMovies(id: Int?) {
        viewModelScope.launch {
            moviesFilterUseCase(id = id).collect {
                when (it) {
                    is Resource.Success -> {
                        _listState.update { currentState ->
                            currentState.copy(movies = it.data.toPresenter().results)
                        }
                    }

                    is Resource.Error -> errorMessage(message = it.errorMessage)

                    is Resource.Loading -> {
                        _listState.update { currentState ->
                            currentState.copy(isLoading = it.loading)
                        }
                    }
                }
            }
        }
    }

    private fun searchMovies(title: String) {
        viewModelScope.launch {
            filterMoviesUseCase(title = title).collect {
                when (it) {
                    is Resource.Success -> {
                        _listState.update { currentState ->
                            currentState.copy(movies = it.data.toPresenter().results)
                        }
                    }

                    is Resource.Error -> errorMessage(message = it.errorMessage)

                    is Resource.Loading -> {
                        _listState.update { currentState ->
                            currentState.copy(isLoading = it.loading)
                        }
                    }
                }
            }
        }
    }

    private fun errorMessage(message: String?) {
        _listState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    private fun navigateToDetailed(id: Int) {
        viewModelScope.launch {
            _uiEvent.emit(ListUIEvent.NavigateToDetailed(id = id))
        }
    }

    private fun navigateToMain() {
        viewModelScope.launch {
            _uiEvent.emit(ListUIEvent.NavigateToMain)
        }
    }

    sealed interface ListUIEvent {
        data class NavigateToDetailed(val id: Int) : ListUIEvent
        object NavigateToMain : ListUIEvent
    }
}