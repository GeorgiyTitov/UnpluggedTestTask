package com.gt.domain.product

interface ProductRepository {
    suspend fun saveLocally(products: List<Product>)
    suspend fun fetchFromRemote(): List<Product>
    suspend fun searchLocally(query: String): List<Product>
    suspend fun getProductByIdFromRemote(id: String): Product
}
