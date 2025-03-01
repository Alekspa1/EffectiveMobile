package com.drag0n.effectivemobile.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VacancyRoom(
    @PrimaryKey()val id: String,
    //@ColumnInfo(name = "isFavorite")val isFavorite: Boolean,
)
