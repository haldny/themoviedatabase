package com.haldny.themoviedatabase.entity.es

import com.haldny.themoviedatabase.entity.CreditResponse
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "movie", type = "movie")
data class MovieES(@Id val id: String? = null,
                   val movie: ConvertedMovieES? = null,
                   val credit: CreditResponse? = null)