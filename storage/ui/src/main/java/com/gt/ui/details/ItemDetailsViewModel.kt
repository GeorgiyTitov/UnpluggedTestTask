package com.gt.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gt.domain.product.Product
import com.gt.domain.usecases.FetchRemoteItemDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor(
    private val fetchRemoteItemDetailsUseCase: FetchRemoteItemDetailsUseCase
) : ViewModel() {

    val productData = MutableLiveData<Product>()
    val isLoading = MutableLiveData(false)

    fun getProductById(id: String) {
        if (isLoading.value == true) return
        viewModelScope.launch {
            isLoading.postValue(true)

            val product = fetchRemoteItemDetailsUseCase.invoke(id)

            productData.postValue(product)
            isLoading.postValue(false)
        }
    }
}
