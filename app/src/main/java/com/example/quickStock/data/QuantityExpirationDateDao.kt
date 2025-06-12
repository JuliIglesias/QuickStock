package com.example.quickStock.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface QuantityExpirationDateDao {
    @Insert
    suspend fun insert(quantityExpirationDate: QuantityExpirationDate): Long

    @Update
    suspend fun update(quantityExpirationDate: QuantityExpirationDate)

    @Delete
    suspend fun delete(quantityExpirationDate: QuantityExpirationDate)

    @Query("SELECT * FROM quantity_expiration_dates WHERE productId = :productId")
    suspend fun getByProductId(productId: String): List<QuantityExpirationDate>
}

