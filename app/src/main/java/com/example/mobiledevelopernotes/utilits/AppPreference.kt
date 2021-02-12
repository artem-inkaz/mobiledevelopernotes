package com.example.mobiledevelopernotes.utilits

import android.content.Context
import android.content.SharedPreferences

object AppPreference {

    private const val INIT_USER = "initUser"
    private const val TYPE_DB = "typeDB"
    private const val NAME_PREF = "preference"

    private lateinit var mPreferences: SharedPreferences

    // получаем настройки из контекста
    fun  getPreference(context: Context):SharedPreferences{
        mPreferences = context.getSharedPreferences(NAME_PREF,Context.MODE_PRIVATE)
        return mPreferences
    }
    // задаем был авторизован пользователь или нет
    fun  setInitUser(init: Boolean){
        mPreferences.edit()
            .putBoolean(INIT_USER,init)
            .apply()
    }
    // передаем тип БД
    fun setTypeDB(type: String){
        mPreferences.edit()
            .putString(TYPE_DB,type)
            .apply()
    }

    // получение из наших настроек авторизован ли юзер
    fun getInitUser():Boolean{
        //в случае ошибки вернем false
        return mPreferences.getBoolean(INIT_USER,false)
    }

    fun getTypeDB(): String{
        return mPreferences.getString(TYPE_DB, TYPE_ROOM).toString()
    }
}