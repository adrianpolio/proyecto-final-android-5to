package com.cibertec.exament2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Insert
    suspend fun insert(product: Product)

    @Query("SELECT * FROM products")
    suspend fun getList(): List<Product>

}