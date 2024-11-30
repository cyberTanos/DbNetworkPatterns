package com.example.dbnetworkpatterns.data

import com.example.dbnetworkpatterns.App
import com.example.dbnetworkpatterns.domain.entity.Bouquet
import com.example.dbnetworkpatterns.domain.entity.BouquetFlowerRelation
import com.example.dbnetworkpatterns.domain.entity.Flower

suspend fun initDatabase() {
    val db = App.database
    val flowerDao = db.flowerDao()
    val bouquetDao = db.bouquetDao()
    val relationDao = db.bouquetFlowerRelationDao()

    if (bouquetDao.getAllBouquets().isNotEmpty()) return

    flowerDao.insertAll(
        Flower(name = "Роза", color = "Красный", stock = 50),
        Flower(name = "Тюльпан", color = "Белый", stock = 30),
        Flower(name = "Лилия", color = "Розовый", stock = 20)
    )

    bouquetDao.insertAll(
        Bouquet(name = "Романтический"),
        Bouquet(name = "Весенний")
    )

    relationDao.insertAll(
        BouquetFlowerRelation(bouquetId = 1, flowerId = 1, quantity = 3),
        BouquetFlowerRelation(bouquetId = 1, flowerId = 2, quantity = 2),
        BouquetFlowerRelation(bouquetId = 2, flowerId = 2, quantity = 4),
        BouquetFlowerRelation(bouquetId = 2, flowerId = 3, quantity = 5)
    )
}
