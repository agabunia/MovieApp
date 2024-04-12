package com.example.midterm_project.presentation.mapper.main

import com.example.midterm_project.domain.model.main.MovieList
import com.example.midterm_project.presentation.model.main.Movies
import java.math.RoundingMode

fun MovieList.toPresenter() : Movies {
    val movieListDetails = results.map {
        Movies.Detail(
            id = it.id,
            title = it.title,
            poster = it.poster,
            vote =  it.vote.toDouble().toBigDecimal().setScale(2, RoundingMode.UP).toString(),
            releaseDate = it.releaseDate
        )
    }
    return Movies(results = movieListDetails)
}