package com.mohamedmabrouk.estarta_task.presentation.productList

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamedmabrouk.estarta_task.domain.GetProductsUseCase
import com.mohamedmabrouk.estarta_task.presentation.State
import com.mohamedmabrouk.estarta_task.source.model.Product
import com.mohamedmabrouk.estarta_task.utils.ErrorResolver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val errorResolver: ErrorResolver
) : ViewModel() {

    private val _productList = MutableLiveData<List<Product?>?>()
    val productList: LiveData<List<Product?>?>
        get() = _productList

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    private val _errorCode = MutableLiveData<String?>()
    val errorCode: LiveData<String?>
        get() = _errorCode

    private val _errorString = MutableLiveData<String?>()
    val errorString: LiveData<String?>
        get() = _errorString

    fun getAllProducts() {
        viewModelScope.launch {
            getProductsUseCase.execute().collect {
                when (it) {
                    is State.DataError -> {
                        _errorCode.postValue(it.errorCode.toString())
                        _dataLoading.postValue(false)
                        _productList.postValue(null)
                        _errorString.postValue(errorResolver.resolveError(errorCode.value?.toInt()))
                    }
                    is State.Loading -> {
                        _dataLoading.postValue(true)
                        _errorCode.postValue(null)
                        _productList.postValue(null)
                        _errorString.postValue(null)
                    }
                    is State.Success -> {
                        _productList.postValue(it.data?.results)
                        _dataLoading.postValue(false)
                        _errorCode.postValue(null)
                        _errorString.postValue(null)
                    }
                }
            }
        }
    }
}