package com.example.mobiledevelopernotes.database

import androidx.lifecycle.LiveData
import com.example.mobiledevelopernotes.models.AppNote

interface DataBaseRepository {
    // хранит записи LiveData в виде списка
    val allNotes: LiveData<List<AppNote>>
    // добавление записи, удаление
    // suspend для коррутин
    suspend fun insert(note: AppNote, onSuccess:()-> Unit)
    suspend fun delete(note: AppNote, onSuccess:()-> Unit)

    fun connectToDataBase(onSuccess: () -> Unit,onFaiil: (String) -> Unit){ }

    fun signOut(){}
}
