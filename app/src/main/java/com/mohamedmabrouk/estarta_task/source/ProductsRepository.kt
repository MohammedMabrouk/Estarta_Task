package com.mohamedmabrouk.estarta_task.source

import com.mohamedmabrouk.estarta_task.presentation.State
import com.mohamedmabrouk.estarta_task.source.model.ProductsApiResponse
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(): Flow<State<ProductsApiResponse>>
}