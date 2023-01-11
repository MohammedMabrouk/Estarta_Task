package com.mohamedmabrouk.estarta_task.source

import com.mohamedmabrouk.estarta_task.presentation.State
import com.mohamedmabrouk.estarta_task.source.model.ProductsApiResponse

interface ProductsDataSource {
    suspend fun getProductsList(): State<ProductsApiResponse>
}