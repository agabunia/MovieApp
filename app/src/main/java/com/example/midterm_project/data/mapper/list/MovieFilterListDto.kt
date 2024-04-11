package com.example.midterm_project.data.mapper.list

import com.example.midterm_project.data.model.list.MovieFilterListDto
import com.example.midterm_project.domain.model.list.MovieFilterList

fun MovieFilterListDto.toDomain(): MovieFilterList {
    val results = results.map {
        MovieFilterList.MovieFilter(
            id = it.id,
            title = it.title,
            poster = it.poster,
            vote = it.vote,
            releaseDate = it.releaseDate,
            genresId = it.genresId
        )
    }
    return MovieFilterList(results = results)
}