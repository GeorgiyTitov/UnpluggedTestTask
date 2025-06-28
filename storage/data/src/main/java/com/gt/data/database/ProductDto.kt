package com.gt.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gt.domain.product.ProductData

@Entity(tableName = "products")
@TypeConverters(ProductDataConverter::class)
data class ProductDto(
    @PrimaryKey val id: String,
    val name: String,
    val data: ProductData?
)
