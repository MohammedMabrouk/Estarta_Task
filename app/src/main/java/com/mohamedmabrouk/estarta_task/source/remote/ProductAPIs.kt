package com.mohamedmabrouk.estarta_task.source.remote

import com.mohamedmabrouk.estarta_task.source.model.ProductsApiResponse
import retrofit2.http.GET

interface ProductAPIs {
    @GET("default/dynamodb-writer")
    suspend fun getAllProducts(): ProductsApiResponse
}