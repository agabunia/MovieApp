package com.example.midterm_project.data.service.movie_detailed

import com.example.midterm_project.data.model.movie_detailed.MovieDetailedDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MovieDetailedService {
    @GET("https://api.themoviedb.org/3/movie/{id}?language=en-US")
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNmQ1ZTlhNGFlODBiODJjMDU5YTcwYzlhMzY3YjYwYSIsInN1YiI6IjY1YTRjYWQ0MGQxMWYyMDEyMzI3MjM4NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.i4YQ-eB5M4Zuekw7hR1C4kBXDpLYwo5ZU-1UTXemuoQ"
    )
    suspend fun getMovieDetailed(@Path("id") id: Int): Response<MovieDetailedDto>
}