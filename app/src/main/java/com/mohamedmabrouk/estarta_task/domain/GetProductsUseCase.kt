package com.mohamedmabrouk.estarta_task.domain

import com.mohamedmabrouk.estarta_task.presentation.State
import com.mohamedmabrouk.estarta_task.source.ProductsRepository
import com.mohamedmabrouk.estarta_task.source.model.ProductsApiResponse
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val productsRepository: ProductsRepository) {
    suspend fun execute(): State<ProductsApiResponse> {
        return productsRepository.getProducts()
    }
}