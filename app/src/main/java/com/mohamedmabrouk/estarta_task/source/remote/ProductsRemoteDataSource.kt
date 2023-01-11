package com.mohamedmabrouk.estarta_task.source.remote

import com.mohamedmabrouk.estarta_task.presentation.State
import com.mohamedmabrouk.estarta_task.source.ProductsDataSource
import com.mohamedmabrouk.estarta_task.source.model.Product
import com.mohamedmabrouk.estarta_task.source.model.ProductsApiResponse
import com.mohamedmabrouk.estarta_task.utils.Constants.NETWORK_ERROR
import com.mohamedmabrouk.estarta_task.utils.Constants.NO_DATA
import com.mohamedmabrouk.estarta_task.utils.Constants.NO_INTERNET_CONNECTION
import com.mohamedmabrouk.estarta_task.utils.NetworkState
import java.io.IOException
import javax.inject.Inject

class ProductsRemoteDataSource @Inject constructor(
    private val networkState: NetworkState,
    private val productAPIs: ProductAPIs
) : ProductsDataSource {

    override suspend fun getProductsList(): State<ProductsApiResponse> {
        return when (val response = processCall(productAPIs::getAllProducts)) {
            is List<*> -> {
                State.Success(
                    data = ProductsApiResponse(
                        null,
                        response as ArrayList<Product>,
                    )
                )
            }
            else -> {
                State.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> ProductsApiResponse): Any? {
        if (!networkState.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            // since API is not providing statusCode, we're checking on the nullability.
            if (response.results.isNullOrEmpty()) {
                NO_DATA
            } else {
                response.results
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}