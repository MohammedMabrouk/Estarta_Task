package com.mohamedmabrouk.estarta_task.source

import com.mohamedmabrouk.estarta_task.presentation.State
import com.mohamedmabrouk.estarta_task.source.model.ProductsApiResponse
import com.mohamedmabrouk.estarta_task.source.remote.ProductsRemoteDataSource
import javax.inject.Inject

class DefaultProductsRepository @Inject constructor(private val productsRemoteDataSource: ProductsRemoteDataSource) :
    ProductsRepository {

    override suspend fun getProducts(): State<ProductsApiResponse> {
        return productsRemoteDataSource.getProductsList()
    }
}