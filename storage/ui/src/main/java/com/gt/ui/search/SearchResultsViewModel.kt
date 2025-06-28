package com.gt.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gt.domain.product.Product
import com.gt.domain.usecases.SearchInDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultsViewModel @Inject constructor(
    private val searchInDatabaseUseCase: SearchInDatabaseUseCase,
) : ViewModel() {

    val foundProducts = MutableLiveData<List<Product>>()

    fun searchProducts(query: String) {
        viewModelScope.launch {
            val products = searchInDatabaseUseCase.invoke(query)
            foundProducts.postValue(products)
        }
    }
}
