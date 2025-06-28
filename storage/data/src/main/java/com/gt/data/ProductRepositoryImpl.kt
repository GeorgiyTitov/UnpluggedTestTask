package com.gt.data

import com.gt.data.database.AppDatabase
import com.gt.data.database.ProductDao
import com.gt.data.mappers.ProductMapper
import com.gt.data.remote.RetrofitService
import com.gt.domain.product.Product
import com.gt.domain.product.ProductRepository

class ProductRepositoryImpl(
    val localSource: ProductDao,
    val remoteSource: RetrofitService,
    val mapper: ProductMapper
) : ProductRepository {
    override suspend fun saveLocally(products: List<Product>) {
        val productDtos = products.map(mapper::productToDto)
        localSource.insertProducts(productDtos)
    }

    override suspend fun fetchFromRemote(): List<Product> {
        val productsRemote = remoteSource.getProducts()
        return productsRemote.map(mapper::remoteToProduct)
    }

    override suspend fun searchLocally(query: String): List<Product> {
        val foundProductsDto = localSource.search(query)
        return foundProductsDto.map(mapper::dtoToProduct)
    }

    override suspend fun getProductByIdFromRemote(id: String): Product {
        val productRemoteById = remoteSource.getProductById(id = id)
        return mapper.remoteToProduct(productRemoteById)
    }
}
