package com.haldny.themoviedatabase.repository

import com.haldny.themoviedatabase.entity.CreditResponse
import com.haldny.themoviedatabase.entity.MovieResponse

interface FetchMovieRepository {

    fun fetchMovies(title: String): MovieResponse?

    fun fetchCredit(movieId: Int): CreditResponse?
}