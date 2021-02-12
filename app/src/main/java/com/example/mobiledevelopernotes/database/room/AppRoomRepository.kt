package com.example.mobiledevelopernotes.database.room

import androidx.lifecycle.LiveData
import com.example.mobiledevelopernotes.database.DataBaseRepository
import com.example.mobiledevelopernotes.models.AppNote

// реализация репозитория Room
class AppRoomRepository(private val appRoomDao: AppRoomDao):DataBaseRepository {

    override val allNotes: LiveData<List<AppNote>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.Insert(note)
        onSuccess()
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.delete(note)
        onSuccess()
    }

}