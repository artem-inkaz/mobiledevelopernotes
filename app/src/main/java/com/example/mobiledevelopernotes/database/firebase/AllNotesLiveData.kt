package com.example.mobiledevelopernotes.database.firebase

import androidx.lifecycle.LiveData
import com.example.mobiledevelopernotes.models.AppNote
import com.example.mobiledevelopernotes.utilits.REF_DATABASE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

    // чтобы после авторизации попадать на Main Fragment где Recycler без ошибок
class AllNotesLiveData : LiveData<List<AppNote>>() {
    // для работы с FireBase
    //    private val mAuth = FirebaseAuth.getInstance()
    //    private val mDatabaseReference = FirebaseDatabase.getInstance().reference
    // заметки будем наши хранить в notes по идентификатору
    //        .child(mAuth.currentUser?.uid.toString())
    // слушатель
    private val listener = object : ValueEventListener {
        override fun onDataChange(p0: DataSnapshot) {
            // получаем LiveData ввиде map и преобразовали в каждую записку
            value = p0.children.map {
                //если не смогли получить заметку возвращаем пустую
                it.getValue(AppNote::class.java)?: AppNote()
            }
        }
    // срабатывает когда произошли какие то изменение добавили удалили
        override fun onCancelled(error: DatabaseError) {}
    }
    // когда LiveData активна, когда ViewModel будет активна, когда Main Fragment
    // обращается к нашей ViewModel  будет сам активный
    // т.е. если сворачиваем наше приложение или открываем другой фрагмент добавить заметку
    // или посмотреть заметку мы будем слушатели наши отключать
    // LiveData уже не будет слушать БД
    // тем самым будем беречь ресурсы нашей сети

    override fun onActive() {
    // как наша LiveData активна обращаемся к БД
        REF_DATABASE.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        REF_DATABASE.removeEventListener(listener)
        super.onInactive()
    }
}