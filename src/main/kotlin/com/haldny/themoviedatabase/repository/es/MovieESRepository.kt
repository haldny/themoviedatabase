package com.haldny.themoviedatabase.repository.es

import com.haldny.themoviedatabase.entity.es.MovieES
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface MovieESRepository: ElasticsearchRepository<MovieES, String> {

    fun findByMovieTitle(movieTitle: String): List<MovieES>

}