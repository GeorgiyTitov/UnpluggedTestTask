package com.gt.domain.usecases

import com.gt.domain.product.Product
import com.gt.domain.product.ProductRepository

class FetchRemoteItemDetailsUseCase(private val repo: ProductRepository){
    suspend operator fun invoke(id: String): Product {
        return repo.getProductByIdFromRemote(id = id)
    }
}
