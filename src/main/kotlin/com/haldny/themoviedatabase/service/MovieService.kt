package com.haldny.themoviedatabase.service

import com.haldny.themoviedatabase.entity.Movie
import com.haldny.themoviedatabase.entity.es.MovieES

interface MovieService {

    fun saveMovie(title: String) : Movie

    fun getMoviesByTitle(title: String): List<MovieES>

    fun getMoviesBySynopsis(synopsis: String): List<MovieES>

    fun getMoviesByActor(actor: String): List<MovieES>

    fun getMoviesByDirector(director: String): List<MovieES>

    fun getMoviesByTime(startDate: String, endDate: String): List<MovieES>

    fun getMoviesByGenre(genre: Int) : List<MovieES>
}