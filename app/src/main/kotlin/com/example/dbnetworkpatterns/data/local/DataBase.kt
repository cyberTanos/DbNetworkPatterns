package com.example.dbnetworkpatterns.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dbnetworkpatterns.domain.entity.Bouquet
import com.example.dbnetworkpatterns.domain.entity.BouquetFlowerRelation
import com.example.dbnetworkpatterns.domain.entity.Flower

@Database(entities = [Flower::class, Bouquet::class, BouquetFlowerRelation::class], version = 1)
abstract class FlowerShopDatabase : RoomDatabase() {
    abstract fun flowerDao(): FlowerDao
    abstract fun bouquetDao(): BouquetDao
    abstract fun bouquetFlowerRelationDao(): BouquetFlowerRelationDao
}
