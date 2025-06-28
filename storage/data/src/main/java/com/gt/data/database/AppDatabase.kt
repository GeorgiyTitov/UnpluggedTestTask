package com.gt.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ProductDto::class], version = 1)
@TypeConverters(ProductDataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}