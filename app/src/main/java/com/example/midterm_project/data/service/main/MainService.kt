package com.example.midterm_project.data.service.main

import com.example.midterm_project.data.model.main.MovieListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MainService {
   @GET("https://api.themoviedb.org/3/movie/{filter}?language=en-US&page=1")
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNmQ1ZTlhNGFlODBiODJjMDU5YTcwYzlhMzY3YjYwYSIsInN1YiI6IjY1YTRjYWQ0MGQxMWYyMDEyMzI3MjM4NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.i4YQ-eB5M4Zuekw7hR1C4kBXDpLYwo5ZU-1UTXemuoQ"
    )
    suspend fun getMovieList(
        @Path("filter") filter: String,
    ): Response<MovieListDto>
}
