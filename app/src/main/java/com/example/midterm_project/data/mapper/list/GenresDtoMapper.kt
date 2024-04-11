package com.example.midterm_project.data.mapper.list

import com.example.midterm_project.data.model.list.GenresDto
import com.example.midterm_project.domain.model.list.GenresList

fun GenresDto.toDomain(): GenresList {
    val genres = genres.map {
        GenresList.Genres(
            id = it.id,
            name = it.name
        )
    }
    return GenresList(genres = genres)
}