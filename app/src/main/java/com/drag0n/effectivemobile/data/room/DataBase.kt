package com.drag0n.effectivemobile.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.drag0n.effectivemobile.model.VacancyRoom

@Database(entities = [VacancyRoom::class],
    version = 1

)
abstract class DataBase: RoomDatabase()
{

    abstract fun Dao(): Dao

}