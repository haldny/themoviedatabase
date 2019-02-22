package com.haldny.themoviedatabase.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Crew(@SerializedName("job") @Expose var job: String,
                @SerializedName("department") @Expose var department: String,
                @SerializedName("credit_id") @Expose var creditId: String,
                @SerializedName("gender") @Expose var gender: Int,
                @SerializedName("id") @Expose var id: Int,
                @SerializedName("name") @Expose var name: String,
                @SerializedName("profile_path") @Expose var profilePath: String?) {

    constructor() : this ("", "", "", 0, 0, "", "")

}