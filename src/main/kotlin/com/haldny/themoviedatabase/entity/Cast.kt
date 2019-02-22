package com.haldny.themoviedatabase.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Cast(@SerializedName("cast_id") @Expose var cast_id: Int,
                @SerializedName("character") @Expose var character: String,
                @SerializedName("credit_id") @Expose var creditId: String,
                @SerializedName("gender") @Expose var gender: Int,
                @SerializedName("id") @Expose var id: Int,
                @SerializedName("name") @Expose var name: String,
                @SerializedName("order") @Expose var order: Int,
                @SerializedName("profile_path") @Expose var profilePath: String?) {

    constructor() : this (0, "", "", 0, 0, "", 0, "")
}