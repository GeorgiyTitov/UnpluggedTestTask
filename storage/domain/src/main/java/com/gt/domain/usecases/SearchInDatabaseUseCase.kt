package com.gt.domain.usecases

import com.gt.domain.product.Product
import com.gt.domain.product.ProductRepository

class SearchInDatabaseUseCase(private val repo: ProductRepository){
    suspend operator fun invoke(query: String): List<Product> {
        return repo.searchLocally(query = query)
    }
}
