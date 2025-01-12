package com.example.kviz

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OtazkaDao {
    @Insert
    fun insert(otazka: Otazka)

    @Query("SELECT * FROM otazky")
    fun getAllOtazky(): kotlinx.coroutines.flow.Flow<List<Otazka>>


    @Query("DELETE FROM otazky WHERE id = :id")
    fun deleteById(id: Int)

    @Query("DELETE FROM sqlite_sequence WHERE name = 'otazky'")
    fun resetOtazkaId()

    @Query("DELETE FROM otazky")
    fun clearAllOtazky()

}