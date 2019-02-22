package com.haldny.themoviedatabase.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(@SerializedName("page") @Expose var page: Int,
                         @SerializedName("total_results") @Expose var totalResults: Int,
                         @SerializedName("results") @Expose var movies: List<MovieRetrofit>,
                         @SerializedName("total_pages") @Expose var totalPages: Int)