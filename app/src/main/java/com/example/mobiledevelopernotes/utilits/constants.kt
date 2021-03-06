package com.example.mobiledevelopernotes.utilits

import com.example.mobiledevelopernotes.MainActivity
import com.example.mobiledevelopernotes.database.DataBaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_ID: String
lateinit var REF_DATABASE : DatabaseReference
// объявляем глобальные переменные
lateinit var APP_ACTIVITY: MainActivity
const val TYPE_DATABASE = "type database"
const val TYPE_ROOM = "type_room"
lateinit var REPOSITORY:DataBaseRepository
const val TYPE_FIREBASE = "type_firebase"
lateinit var EMAIL: String
lateinit var PASSWORD: String
const val ID_FIREBASE = "idFirebase"
const val NAME = "name"
const val TEXT = "text"