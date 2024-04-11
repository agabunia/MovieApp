package com.example.midterm_project.presentation.mapper.list

import com.example.midterm_project.domain.model.list.MovieFilterList
import com.example.midterm_project.presentation.model.list.MovieFilterModel

fun MovieFilterList.toPresenter(): MovieFilterModel {
    val results = results.map {
        MovieFilterModel.MovieFilterModel(
            id = it.id,
            title = it.title,
            poster = it.poster,
            vote = it.vote,
            releaseDate = it.releaseDate,
            genresId = it.genresId
        )
    }
    return MovieFilterModel(results = results)
}