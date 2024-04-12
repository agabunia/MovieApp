package com.example.midterm_project.presentation.screen.movie_detailed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.domain.repository.movie_detailed.MovieDetailedRepository
import com.example.midterm_project.domain.useCases.movie_detailed.GetMovieDetailedUseCase
import com.example.midterm_project.presentation.event.movie_detailed.MovieDetailedEvent
import com.example.midterm_project.presentation.state.movie_detailed.MovieDetailedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailedViewModel @Inject constructor(
    private val getMovieDetailedUseCase: GetMovieDetailedUseCase
) : ViewModel() {

    private val _movieDetailedState = MutableStateFlow(MovieDetailedState())
    val movieDetailedState: SharedFlow<MovieDetailedState> = _movieDetailedState.asStateFlow()

    fun onEvent(event: MovieDetailedEvent) {
        when(event) {
            is MovieDetailedEvent.FetchMovie -> fetchMovie(event.id)
            else -> {}
        }
    }

    private fun fetchMovie(id: Int) {
        viewModelScope.launch {
            getMovieDetailedUseCase(id = id).collect{
                when(it) {
                    is Resource.Success -> {
                        _movieDetailedState.update { currentState -> currentState.copy(movieDetailed = it.data) }
                    }

                    is Resource.Error -> {
                        _movieDetailedState.update { currentState -> currentState.copy(errorMessage = it.errorMessage) }
                    }

                    is Resource.Loading -> {
                        _movieDetailedState.update { currentState -> currentState.copy(isLoading = it.loading) }
                    }

                }
            }
        }
    }

}
