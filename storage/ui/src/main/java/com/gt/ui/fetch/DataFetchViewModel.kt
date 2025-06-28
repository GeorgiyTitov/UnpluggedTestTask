package com.gt.ui.fetch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gt.domain.product.Product
import com.gt.domain.usecases.FetchRemoteDataUseCase
import com.gt.domain.usecases.SaveDataToLocalDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataFetchViewModel @Inject constructor(
    private val fetchRemoteDataUseCase: FetchRemoteDataUseCase,
    private val saveDataToLocalDatabaseUseCase: SaveDataToLocalDatabaseUseCase,
) : ViewModel() {

    var products = listOf<Product>()
    val isLoading = MutableLiveData<Boolean>(false)
    val isDataFetched = MutableLiveData<Boolean>(false)
    val isDataSavedLocally = MutableLiveData<Boolean>(false)

    fun fetchProducts() {
        viewModelScope.launch {
            isLoading.postValue(true)

            products = fetchRemoteDataUseCase.invoke()

            isDataFetched.postValue(true)
            isLoading.postValue(false)
        }
    }

    fun saveProducts() {
        viewModelScope.launch {
            isLoading.postValue(true)

            saveDataToLocalDatabaseUseCase.invoke(products)
            isDataSavedLocally.postValue(true)
            isLoading.postValue(false)
        }
    }
}
