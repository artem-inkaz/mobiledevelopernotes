package com.example.mobiledevelopernotes.screens.add_new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiledevelopernotes.models.AppNote
import com.example.mobiledevelopernotes.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteViewModel(application: Application) : AndroidViewModel(application) {
    // метод который вставляет заметку в БД
    fun insert(note: AppNote, onSuccess: () -> Unit) =
            viewModelScope.launch(Dispatchers.IO) {
                REPOSITORY.insert(note) {
                    onSuccess()
                }
            }
}