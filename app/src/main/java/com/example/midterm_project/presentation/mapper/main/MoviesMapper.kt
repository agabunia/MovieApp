package com.example.midterm_project.presentation.mapper.main

import com.example.midterm_project.domain.model.main.MovieList
import com.example.midterm_project.presentation.model.main.Movies

fun MovieList.toPresenter() : Movies {
    val movieListDetails = results.map {
        Movies.Detail(
            id = it.id,
            title = it.title,
            poster = it.poster,
            vote = it.vote,
            releaseDate = it.releaseDate
        )
    }
    return Movies(results = movieListDetails)
}