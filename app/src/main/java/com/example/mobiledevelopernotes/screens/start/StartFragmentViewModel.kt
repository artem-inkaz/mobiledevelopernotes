package com.example.mobiledevelopernotes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
// AndroidViewModel позволяет напрямую работать с контекстом и предотвращение утечки памяти
class StartFragmentViewModel(application: Application):AndroidViewModel(application) {
    private val mContext = application
    //принимаем параметр TYPE_ROOM по клику в StartFragment ф-я initialization
    fun initDatabase(type:String){

    }
}