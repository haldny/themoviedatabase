package com.haldny.themoviedatabase.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

data class CreditResponse(@SerializedName("id") @Expose var id: Int,
                          @SerializedName("cast") @Expose @Field( type = FieldType.Nested) var casts: List<Cast>,
                          @SerializedName("crew") @Expose @Field( type = FieldType.Nested) var crews: List<Crew>) {

    constructor() : this(0, listOf(), listOf())

}
