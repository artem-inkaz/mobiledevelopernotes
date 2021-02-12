package com.example.mobiledevelopernotes.database.firebase

import androidx.lifecycle.LiveData
import com.example.mobiledevelopernotes.database.DataBaseRepository
import com.example.mobiledevelopernotes.models.AppNote
import com.example.mobiledevelopernotes.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AppFireBaseRepository: DataBaseRepository {

    // экземпляры для работы м БД
    private val mAuth = FirebaseAuth.getInstance()
    // получаем ссылку на нашу LiveData в AllNotesLiveData
    private val mDatabaseReference = FirebaseDatabase.getInstance().reference
        // заметки будем наши хранить в notes по идентификатору
        .child(mAuth.currentUser?.uid.toString())

    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()
    // принимаем заметку и CallBack  об успешной операции
    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        // получаем ссылку на нашу LiveData в AllNotesLiveData
        // вернем уникальный ключ нашей заметки
        val idNote = mDatabaseReference.push().key.toString()
        // для удобства обновления всех элементов в Notes необходимо создать map
        val mapNote = hashMapOf<String, Any>()
        //
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text
        // обращаемся к БД
        mDatabaseReference.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess ()}
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun connectToDataBase(onSuccess: () -> Unit, onFaiil: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener() {
                // если не успешно то создаем
                mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFaiil(it.message.toString()) }
            }
    }

    override fun signOut() {
        mAuth.signOut()
    }
}