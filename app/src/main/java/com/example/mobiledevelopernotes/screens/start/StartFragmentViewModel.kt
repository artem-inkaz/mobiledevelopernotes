package com.example.mobiledevelopernotes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mobiledevelopernotes.database.room.AppRoomDatabase
import com.example.mobiledevelopernotes.database.room.AppRoomRepository
import com.example.mobiledevelopernotes.utilits.REPOSITORY
import com.example.mobiledevelopernotes.utilits.TYPE_ROOM

// AndroidViewModel позволяет напрямую работать с контекстом и предотвращение утечки памяти
class StartFragmentViewModel(application: Application):AndroidViewModel(application) {
    private val mContext = application
    //принимаем параметр TYPE_ROOM по клику в StartFragment ф-я initialization
    // когда мы запускаем наше приложение мы видим что у нас есть Room
    fun initDatabase(type:String, onSuccess:()-> Unit){
    //при инициализации принимаем тип БД
        when(type){
            TYPE_ROOM -> {
// реализация репозитория Room
//class AppRoomRepository(private val appRoomDao: AppRoomDao):DataBaseRepository {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                // когда нажимаем на Room инициализируем наш репозиторий
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
        }
    }
}