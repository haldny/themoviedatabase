package com.haldny.themoviedatabase.repository

import com.haldny.themoviedatabase.entity.CreditResponse
import com.haldny.themoviedatabase.entity.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET("search/movie")
    fun getMovies(@Query("api_key") apiKey: String,
                  @Query("query", encoded = true) title: String): Call<MovieResponse>

    @GET("movie/{id}/credits")
    fun getCredit(@Path(value = "id", encoded = true) movieId: Int,
                  @Query("api_key") apiKey: String): Call<CreditResponse>

}