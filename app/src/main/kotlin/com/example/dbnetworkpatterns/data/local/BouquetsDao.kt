package com.example.dbnetworkpatterns.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dbnetworkpatterns.domain.entity.Bouquet

@Dao
interface BouquetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg bouquets: Bouquet)

    @Query("SELECT * FROM bouquets")
    suspend fun getAllBouquets(): List<Bouquet>
}
