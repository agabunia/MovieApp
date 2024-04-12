package com.example.midterm_project.domain.model.list

data class MovieFilterList(
    val results: List<MovieFilter>
) {
    data class MovieFilter(
        val id: Int,
        val title: String,
        val poster: String?,
        val vote: String,
        val releaseDate: String,
        val genresId: List<Int>
    )
}