package com.haldny.themoviedatabase.repository

import com.haldny.themoviedatabase.entity.Movie
import org.springframework.data.mongodb.repository.MongoRepository

interface MovieRepository: MongoRepository<Movie, String> {
}