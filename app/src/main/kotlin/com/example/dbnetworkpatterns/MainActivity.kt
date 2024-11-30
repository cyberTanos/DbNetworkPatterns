package com.example.dbnetworkpatterns

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dbnetworkpatterns.data.initDatabase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val db = App.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            initDatabase()
            showAvailableBouquets()
            buyBouquet(1)
        }
    }

    private suspend fun showAvailableBouquets() {
        val bouquets = db.bouquetDao().getAllBouquets()
        val relationDao = db.bouquetFlowerRelationDao()

        for (bouquet in bouquets) {
            val flowers = relationDao.getFlowersForBouquet(bouquet.id)
            Log.d("Bouquet", "Букет: ${bouquet.name}, Оформление: ${bouquet.decoration}, id: ${bouquet.id}")
            for (flower in flowers) {
                Log.d("Bouquet", " - ${flower.quantity}x ${flower.name} (${flower.color}) - ${flower.country}")
            }
        }
    }

    private suspend fun buyBouquet(bouquetId: Int) {
        val relationDao = db.bouquetFlowerRelationDao()
        val flowerDao = db.flowerDao()

        val flowers = relationDao.getFlowersForBouquet(bouquetId)
        for (flower in flowers) {
            val result = flowerDao.reduceStock(flowerId = flower.id, quantity = flower.quantity)
            if (result == 0) {
                Log.e("Bouquet", "Недостаточно цветов: ${flower.name} (${flower.color})")
                return
            }
        }
        Log.d("Bouquet", "Букет с ID $bouquetId успешно куплен!")
    }
}
