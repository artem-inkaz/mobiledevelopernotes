package com.example.mobiledevelopernotes.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobiledevelopernotes.models.AppNote

@Database(entities = [AppNote::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    // получение объекта DAO
    abstract fun getAppRoomDao(): AppRoomDao

    // создаем синглтон чтобы создался один экземляр БД
    // проверяем на наличие созданной БД иначе создаем или используем имеющуюся
    companion object {
        //БД не должна кэшироваться чтобы получать всегда актуальные значения
        @Volatile
        private var database: AppRoomDatabase? = null

        // ф-я которая будет предоставлятьь экземпляр БД
        //запретим чтобы к функции могли обращаться несколько экземпляров одновременно
        @Synchronized
        fun getInstance(context: Context): AppRoomDatabase {
            if (database == null) {
                // инициализируем создаем  БД
                database = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "database"
                ).build()
                return database as AppRoomDatabase
            } else return database as AppRoomDatabase
        }
    }
}
