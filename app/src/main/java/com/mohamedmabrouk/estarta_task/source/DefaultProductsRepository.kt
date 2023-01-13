package com.mohamedmabrouk.estarta_task.source

import com.mohamedmabrouk.estarta_task.presentation.State
import com.mohamedmabrouk.estarta_task.source.model.ProductsApiResponse
import com.mohamedmabrouk.estarta_task.source.remote.ProductsRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultProductsRepository @Inject constructor(private val productsRemoteDataSource: ProductsRemoteDataSource) :
    ProductsRepository {

    override suspend fun getProducts(): Flow<State<ProductsApiResponse>> {
        return flow {
            emit(State.Loading())
            emit(productsRemoteDataSource.getProductsList())
        }.flowOn(Dispatchers.IO)
    }
}