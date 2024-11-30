package com.example.dbnetworkpatterns.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dbnetworkpatterns.domain.BouquetFlowerDetail
import com.example.dbnetworkpatterns.domain.entity.BouquetFlowerRelation

@Dao
interface BouquetFlowerRelationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg relations: BouquetFlowerRelation)

    @Query(
        """
        SELECT f.id ,f.name, f.color, r.quantity 
        FROM bouquet_flower_relation r 
        JOIN flowers f ON r.flowerId = f.id 
        WHERE r.bouquetId = :bouquetId
    """
    )
    suspend fun getFlowersForBouquet(bouquetId: Int): List<BouquetFlowerDetail>
}
