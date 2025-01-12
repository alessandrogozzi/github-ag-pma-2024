package com.example.kviz

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "otazky")
data class Otazka(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val textOtazky: String,
    val odpoved1: String,
    val odpoved2: String,
    val odpoved3: String,
    val odpoved4: String,
    val spravnaOdpoved: Int
)