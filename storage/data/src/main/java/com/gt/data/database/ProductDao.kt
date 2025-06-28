package com.gt.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertProducts(products: List<ProductDto>)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<ProductDto>

    @Query(
        "SELECT * FROM products " +
                "WHERE id   LIKE '%' || :query || '%' " +
                "OR   name LIKE '%' || :query || '%' " +
                "OR   data LIKE '%' || :query || '%'"
    )
    suspend fun search(query: String): List<ProductDto>
}
