package com.example.midterm_project.data.service.list

import com.example.midterm_project.data.model.list.MovieFilterListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieFilterService {
    @GET("https://api.themoviedb.org/3/discover/movie")
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNmQ1ZTlhNGFlODBiODJjMDU5YTcwYzlhMzY3YjYwYSIsInN1YiI6IjY1YTRjYWQ0MGQxMWYyMDEyMzI3MjM4NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.i4YQ-eB5M4Zuekw7hR1C4kBXDpLYwo5ZU-1UTXemuoQ"
    )
    suspend fun getMovieList(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int? = null,
    ): Response<MovieFilterListDto>
}
