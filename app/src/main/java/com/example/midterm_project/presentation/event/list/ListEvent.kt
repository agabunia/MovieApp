package com.example.midterm_project.presentation.event.list

sealed class ListEvent {
    object FetchGenres : ListEvent()
    data class FetchMovies(val id: Int? = null) : ListEvent()
    data class FetchSearchedMovies(val title: String) : ListEvent()
    data class NavigateToDetailed(val id: Int) : ListEvent()
    object NavigateToMain : ListEvent()
    object ResetErrorMessage: ListEvent()
}