package com.example.kviz

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Otazka::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun otazkaDao(): OtazkaDao
}