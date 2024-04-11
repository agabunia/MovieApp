package com.example.midterm_project.presentation.mapper.list

import com.example.midterm_project.domain.model.list.GenresList
import com.example.midterm_project.presentation.model.list.GenresModel

//fun GenresList.Genres.toPresenter(): GenresModel.Genres {
//    return GenresModel.Genres(id = id, name = name)
//}

fun GenresList.toPresenter(): GenresModel {
    val genres = genres.map {
        GenresModel.Genres(
            id = it.id,
            name = it.name
        )
    }
    return GenresModel(genres = genres)
}