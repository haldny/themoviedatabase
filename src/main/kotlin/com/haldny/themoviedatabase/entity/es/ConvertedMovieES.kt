package com.haldny.themoviedatabase.entity.es

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.haldny.themoviedatabase.entity.CreditResponse
import com.haldny.themoviedatabase.entity.MovieRetrofit
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import java.text.SimpleDateFormat
import java.util.*

data class ConvertedMovieES(@SerializedName("id") @Expose var id: Int,
                            @SerializedName("title") @Expose var title: String,
                            @SerializedName("poster_path") @Expose var posterPath: String?,
                            @SerializedName("release_date") @Expose var releaseDate: Date?,
                            @SerializedName("vote_average") @Expose var rating: String,
                            @SerializedName("genre_ids") @Expose var genreIds: List<Int>,
                            @SerializedName("overview") @Expose var overview: String?) {

    constructor(retrofit: MovieRetrofit) : this(retrofit.id, retrofit.title, retrofit.posterPath,
            null, retrofit.rating, retrofit.genreIds, retrofit.overview) {
        if (retrofit.releaseDate != null) {
            try {
                System.out.println("Release Date String: " + retrofit.releaseDate)
                System.out.println("Release Date Formatted: " + SimpleDateFormat("yyyy-MM-dd").parse(retrofit.releaseDate))
                releaseDate = SimpleDateFormat("yyyy-MM-dd").parse(retrofit.releaseDate)
            } catch (e: Exception) {
                System.out.println(e.message)
            }
        }
    }

    constructor() : this(0, "", "", null, "", listOf(), "")

}