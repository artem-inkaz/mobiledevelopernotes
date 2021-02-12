package com.example.mobiledevelopernotes.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiledevelopernotes.models.AppNote
import com.example.mobiledevelopernotes.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application): AndroidViewModel(application) {
    // принимает заметку и возвращает CallBack так как задача выполнена правильно
    fun  delete(note: AppNote, onSuccess: () -> Unit) =
        // viewModel не знает как работает REPOSITORY просто дает команду удалить запись из БД
        viewModelScope.launch(Dispatchers.IO) {
            // обращаемся к REPOSITORY
            REPOSITORY.delete(note){
                onSuccess()
            }
        }
}