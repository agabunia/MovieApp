package com.example.midterm_project.presentation.event.movie_detailed

sealed class MovieDetailedEvent {
    data class FetchMovie(val id: Int) : MovieDetailedEvent()
    object ResetErrorMessage : MovieDetailedEvent()
    object NavigateBack: MovieDetailedEvent()
}