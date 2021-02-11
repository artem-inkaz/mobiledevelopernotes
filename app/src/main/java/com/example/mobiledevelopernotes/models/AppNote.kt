package com.example.mobiledevelopernotes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// данные
@Entity(tableName = "notes_tables")
data class AppNote (
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    @ColumnInfo val name: String = "",
    @ColumnInfo val text: String = ""
)