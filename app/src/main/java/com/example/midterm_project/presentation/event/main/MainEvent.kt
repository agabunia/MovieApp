package com.example.midterm_project.presentation.event.main

sealed class MainEvent {
    data class FetchMovies(val filters: List<Pair<String, String>>) : MainEvent()
    object MovieList: MainEvent()
    object Account: MainEvent()
    data class NavigateToDetailed(val id: Int): MainEvent()
}
