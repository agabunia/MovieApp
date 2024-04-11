package com.example.midterm_project.presentation.event.list

sealed class ListEvent {
    object FetchGenres : ListEvent()
    data class FetchMovies(val id: Int? = null): ListEvent()
}