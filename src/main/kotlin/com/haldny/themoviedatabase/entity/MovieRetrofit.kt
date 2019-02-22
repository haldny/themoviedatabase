package com.haldny.themoviedatabase.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieRetrofit(@SerializedName("id") @Expose var id: Int,
                         @SerializedName("title") @Expose var title: String,
                         @SerializedName("poster_path") @Expose var posterPath: String?,
                         @SerializedName("release_date") @Expose var releaseDate: String?,
                         @SerializedName("vote_average") @Expose var rating: String,
                         @SerializedName("genre_ids") @Expose var genreIds: List<Int>,
                         @SerializedName("overview") @Expose var overview: String?)