package com.example.dbnetworkpatterns

import android.app.Application
import androidx.room.Room
import com.example.dbnetworkpatterns.data.local.FlowerShopDatabase
import com.example.dbnetworkpatterns.data.local.MIGRATION_1_2

class App : Application() {

    companion object {
        lateinit var database: FlowerShopDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            FlowerShopDatabase::class.java,
            "database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}
