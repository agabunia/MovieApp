package com.example.midterm_project.presentation.model.list

data class GenresModel(
    val genres: List<Genres>
) {
    data class Genres(
        val id: Int,
        val name: String,
        var isClicked: Boolean = false
    )
}
