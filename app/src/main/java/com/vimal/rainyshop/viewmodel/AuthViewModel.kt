package com.vimal.rainyshop.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.vimal.rainyshop.model.UserModel

class AuthViewModel: ViewModel() {
    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    fun login() {

    }

    fun signup(email: String, name: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    // Assign UserID to account
                    var userId = it.result?.user?.uid

                    // Store UserID in UserModel along with name and email
                    val userModel = UserModel(name, email, userId!!)

                    firestore.collection("users")
                        .document(userId)
                        .set(userModel)
                        .addOnCompleteListener { dbTask ->
                            if(dbTask.isSuccessful) {
                                onResult(true, null)
                            } else {
                                onResult(false, "Something went wrong...")
                            }
                        }
                } else {
                    onResult(false, it.exception?.localizedMessage)
                }
            }
    }
}