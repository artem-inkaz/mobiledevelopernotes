package com.example.mobiledevelopernotes.utilits

import com.example.mobiledevelopernotes.MainActivity
import com.example.mobiledevelopernotes.database.DataBaseRepository

// объявляем глобальные переменные
lateinit var APP_ACTIVITY: MainActivity
const val TYPE_DATABASE = "type database"
const val TYPE_ROOM = "type_room"

lateinit var REPOSITORY:DataBaseRepository
const val TYPE_FIREBASE = "type_firebase"
lateinit var EMAIL: String
lateinit var PASSWORD: String