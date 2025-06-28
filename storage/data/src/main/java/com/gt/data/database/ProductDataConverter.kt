package com.gt.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.gt.domain.product.ProductData

class ProductDataConverter {
    @TypeConverter
    fun fromProductData(productData: ProductData?): String? {
        return Gson().toJson(productData)
    }

    @TypeConverter
    fun toProductData(productDataJson: String?): ProductData? {
        return Gson().fromJson(productDataJson, ProductData::class.java)
    }
}
