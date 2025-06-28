package com.gt.data.mappers

import com.gt.data.database.ProductDto
import com.gt.data.remote.ProductDataRemote
import com.gt.data.remote.ProductRemote
import com.gt.domain.product.Product
import com.gt.domain.product.ProductData

class ProductMapper {
    fun dtoToProduct(dto: ProductDto): Product {
        return dto.run {
            Product(
                id = id,
                name = name,
                data = data
            )
        }
    }

    fun productToDto(product: Product): ProductDto {
        return product.run {
            ProductDto(
                id = id,
                name = name,
                data = data
            )
        }
    }

    fun remoteToProduct(productRemote: ProductRemote): Product {
        return productRemote.run {
            Product(
                id = id,
                name = name,
                data = data?.toDomain()
            )
        }
    }

    private fun ProductDataRemote.toDomain(): ProductData =
        ProductData(
            color = color,
            capacity = capacity,
            capacityGb = capacityGb,
            price = price,
            generation = generation,
            year = year,
            cpuModel = cpuModel,
            hardDiskSize = hardDiskSize,
            strapColour = strapColour,
            caseSize = caseSize,
            beatsColor = beatsColor,
            description = description,
            iPadCapacity = iPadCapacity,
            screenSize = screenSize,
            iPadGeneration = iPadGeneration
        )
}
