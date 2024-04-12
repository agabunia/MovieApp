package com.example.midterm_project.presentation.model.main

data class Movies(
    val results: List<Detail>
) {
    data class Detail(
        val id: Int,
        val title: String,
        val poster: String?,
        val vote: String,
        val releaseDate: String
    )
}