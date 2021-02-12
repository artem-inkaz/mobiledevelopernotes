package com.example.mobiledevelopernotes.database.firebase

import androidx.lifecycle.LiveData
import com.example.mobiledevelopernotes.database.DataBaseRepository
import com.example.mobiledevelopernotes.models.AppNote
import com.example.mobiledevelopernotes.utilits.EMAIL
import com.example.mobiledevelopernotes.utilits.PASSWORD
import com.google.firebase.auth.FirebaseAuth

class AppFireBaseRepository: DataBaseRepository {

    // экземпляры для работы м БД
    private val mAuth = FirebaseAuth.getInstance()

    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
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