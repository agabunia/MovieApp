package com.example.midterm_project.presentation.state.list

import com.example.midterm_project.presentation.model.list.GenresModel
import com.example.midterm_project.presentation.model.list.MovieFilterModel

data class ListState(
    val genres: List<GenresModel.Genres>? = null,
    val movies: List<MovieFilterModel.MovieFilterModel>? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
