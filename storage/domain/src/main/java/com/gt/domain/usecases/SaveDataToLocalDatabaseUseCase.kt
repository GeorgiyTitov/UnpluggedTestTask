package com.gt.domain.usecases

import com.gt.domain.product.Product
import com.gt.domain.product.ProductRepository

class SaveDataToLocalDatabaseUseCase(private val repo: ProductRepository){
    suspend operator fun invoke(products: List<Product>) {
        repo.saveLocally(products = products)
    }
}
