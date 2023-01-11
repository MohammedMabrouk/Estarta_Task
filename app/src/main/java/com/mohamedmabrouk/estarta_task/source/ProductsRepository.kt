package com.mohamedmabrouk.estarta_task.source

import com.mohamedmabrouk.estarta_task.presentation.State
import com.mohamedmabrouk.estarta_task.source.model.ProductsApiResponse

interface ProductsRepository {
    suspend fun getProducts(): State<ProductsApiResponse>
}