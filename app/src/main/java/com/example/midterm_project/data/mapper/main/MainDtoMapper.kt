package com.example.midterm_project.data.mapper.main

import com.example.midterm_project.data.model.main.MovieListDto
import com.example.midterm_project.domain.model.main.MovieList

fun MovieListDto.toDomain(): MovieList {
    val movieListDetails = results.map {
        MovieList.Detail(
            id = it.id,
            title = it.title,
            poster = it.poster,
            vote = it.vote,
            releaseDate = it.releaseDate
        )
    }
    return MovieList(results = movieListDetails)
}