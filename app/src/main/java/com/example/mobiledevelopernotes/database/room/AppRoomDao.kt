package com.example.mobiledevelopernotes.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mobiledevelopernotes.models.AppNote

@Dao
interface AppRoomDao {
    // Запрос на получение записок ввиде Livedata
    @Query ("SELECT * from notes_tables")
    fun getAllNotes():LiveData<List<AppNote>>
    // если будут какие-то проблемы мы будем игнорировать данную операцию
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun Insert(note: AppNote)
    @Delete
    suspend fun delete(note: AppNote)
}