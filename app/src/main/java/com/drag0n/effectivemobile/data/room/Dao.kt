package com.drag0n.effectivemobile.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.drag0n.effectivemobile.model.VacancyRoom
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Query("SELECT * FROM VacancyRoom")
    fun getAllFlow(): Flow<List<VacancyRoom>>

    @Query("SELECT * FROM VacancyRoom")
    suspend fun getAll(): List<VacancyRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancies(vacancy: VacancyRoom)

    @Delete
    suspend fun deleteVacancies(vacancy: VacancyRoom)

    @Query("SELECT * FROM VacancyRoom WHERE id == :value")
    suspend fun getBoolean(value: String): VacancyRoom?
}