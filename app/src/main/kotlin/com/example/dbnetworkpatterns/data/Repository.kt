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

    if (bouquetDao.getAllBouquets().isEmpty()) {
        flowerDao.insertAll(
            Flower(name = "Роза", color = "Красный", stock = 50, country = "Russia"),
            Flower(name = "Тюльпан", color = "Белый", stock = 30, country = "Australia"),
            Flower(name = "Лилия", color = "Розовый", stock = 20, country = "Russia")
        )

        bouquetDao.insertAll(
            Bouquet(name = "Романтический", decoration = "craft"),
            Bouquet(name = "Весенний", decoration = "basket")
        )

        relationDao.insertAll(
            BouquetFlowerRelation(bouquetId = 1, flowerId = 1, quantity = 3),
            BouquetFlowerRelation(bouquetId = 1, flowerId = 2, quantity = 2),
            BouquetFlowerRelation(bouquetId = 2, flowerId = 2, quantity = 4),
            BouquetFlowerRelation(bouquetId = 2, flowerId = 3, quantity = 5)
        )
    }
    // Миграция на 2 версию бд
    bouquetDao.getAllBouquets().forEach {
        if (it.decoration.isEmpty()) {
            if (it.name == "Романтический") {
                bouquetDao.update(it.copy(decoration = "craft"))
            } else if (it.name == "Весенний") {
                bouquetDao.update(it.copy(decoration = "basket"))
            }
        }
    }
    flowerDao.getAllFlowers().forEach {
        if (it.country.isEmpty()) {
            if (it.name == "Роза" || it.name == "Лилия") {
                flowerDao.update(it.copy(country = "Russia"))
            } else if (it.name == "Тюльпан") {
                flowerDao.update(it.copy(country = "Australia"))
            }
        }
    }
}
