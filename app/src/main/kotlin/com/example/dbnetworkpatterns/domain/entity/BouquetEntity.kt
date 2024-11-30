package com.example.dbnetworkpatterns.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bouquets")
data class Bouquet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
)
