package com.example.midterm_project.presentation.state.main

import com.example.midterm_project.presentation.model.main.Movies
import com.example.midterm_project.presentation.model.main.MoviesFragmentList

data class MainState(
    val movieFragmentList: List<MoviesFragmentList>? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
