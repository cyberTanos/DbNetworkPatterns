package com.example.dbnetworkpatterns.domain.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "bouquet_flower_relation",
    primaryKeys = ["bouquetId", "flowerId"],
    foreignKeys = [
        ForeignKey(entity = Bouquet::class, parentColumns = ["id"], childColumns = ["bouquetId"]),
        ForeignKey(entity = Flower::class, parentColumns = ["id"], childColumns = ["flowerId"])
    ]
)
data class BouquetFlowerRelation(
    val bouquetId: Int,
    val flowerId: Int,
    val quantity: Int
)
