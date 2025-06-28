package com.gt.domain.usecases

import com.gt.domain.product.Product
import com.gt.domain.product.ProductRepository

class FetchRemoteDataUseCase(private val repo: ProductRepository) {
    suspend operator fun invoke(): List<Product> {
        return repo.fetchFromRemote()
    }
}