package com.mohamedmabrouk.estarta_task.source.model


import com.google.gson.annotations.SerializedName

data class ProductsApiResponse(
    @SerializedName("pagination")
    val pagination: Pagination?,
    @SerializedName("results")
    val results: List<Product?>?
){
    data class Pagination(
        @SerializedName("key")
        val key: Any?
    )
}
