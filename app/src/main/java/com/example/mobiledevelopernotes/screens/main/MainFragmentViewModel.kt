package com.example.mobiledevelopernotes.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mobiledevelopernotes.utilits.REPOSITORY

class MainFragmentViewModel(application:Application):AndroidViewModel(application) {
    // получаем ее из REPOSITORY.allNotes
    //  val allNotes - это уже LiveData
    val allNotes = REPOSITORY.allNotes
    //
    fun signOut(){
        REPOSITORY.signOut()
    }

}