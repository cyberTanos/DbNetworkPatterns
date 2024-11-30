package com.example.dbnetworkpatterns.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dbnetworkpatterns.domain.entity.Bouquet
import com.example.dbnetworkpatterns.domain.entity.BouquetFlowerRelation
import com.example.dbnetworkpatterns.domain.entity.Flower

@Database(entities = [Flower::class, Bouquet::class, BouquetFlowerRelation::class], version = 2)
abstract class FlowerShopDatabase : RoomDatabase() {
    abstract fun flowerDao(): FlowerDao
    abstract fun bouquetDao(): BouquetDao
    abstract fun bouquetFlowerRelationDao(): BouquetFlowerRelationDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE bouquets ADD COLUMN decoration TEXT NOT NULL DEFAULT ''")
        db.execSQL("ALTER TABLE flowers ADD COLUMN country TEXT NOT NULL DEFAULT ''")
    }
}
