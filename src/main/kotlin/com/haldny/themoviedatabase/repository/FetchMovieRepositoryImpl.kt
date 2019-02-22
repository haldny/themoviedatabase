package com.haldny.themoviedatabase.repository

import com.haldny.themoviedatabase.entity.CreditResponse
import com.haldny.themoviedatabase.entity.MovieResponse
import org.springframework.stereotype.Repository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Repository
class FetchMovieRepositoryImpl : FetchMovieRepository {

    private var baseUrl: String

    private var apiKey: String

    private val api: TMDBApi

    constructor() {
        baseUrl = "https://api.themoviedb.org/3/"

        apiKey = "d735f93c520c7f3d95ca9c54d7e5d998"

        api = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TMDBApi::class.java)
    }

    override fun fetchMovies(title: String): MovieResponse? {
        val responseMovies = api.getMovies(apiKey, title).execute()
        System.out.println("Response movie: " + responseMovies)
        return responseMovies?.body()
    }

    override fun fetchCredit(movieId: Int): CreditResponse? {
        val responseCredit = api.getCredit(movieId, apiKey).execute()
        System.out.println("Response credit: " + responseCredit)
        return responseCredit?.body()
    }

}