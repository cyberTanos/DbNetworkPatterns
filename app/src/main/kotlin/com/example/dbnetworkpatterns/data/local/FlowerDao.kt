package com.example.dbnetworkpatterns.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dbnetworkpatterns.domain.entity.Flower

@Dao
interface FlowerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg flowers: Flower)

    @Query("UPDATE flowers SET stock = stock - :quantity WHERE id = :flowerId AND stock >= :quantity")
    suspend fun reduceStock(flowerId: Int, quantity: Int): Int

    @Query("SELECT * FROM flowers")
    suspend fun getAllFlowers(): List<Flower>

    @Update
    suspend fun update(flower: Flower)
}
