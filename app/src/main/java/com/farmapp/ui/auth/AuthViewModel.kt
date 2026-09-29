package com.farmapp.ui.auth

import androidx.lifecycle.ViewModel
import com.farmapp.data.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _authState = MutableStateFlow<String?>(null)
    val authState = _authState.asStateFlow()

    fun login(email: String, pass: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (email.isBlank() || pass.isBlank()) {
            onError("Por favor completa todos los campos")
            return
        }
        auth.signInWithEmailAndPassword(email.trim(), pass.trim())
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.localizedMessage ?: "Error de autenticación") }
    }

    fun register(
        nombre: String,
        email: String,
        pass: String,
        rol: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (nombre.isBlank() || email.isBlank() || pass.isBlank()) {
            onError("Por favor completa todos los datos")
            return
        }
        auth.createUserWithEmailAndPassword(email.trim(), pass.trim())
            .addOnSuccessListener { result ->
                val uid = result.user?.uid ?: ""
                val perfil = UserProfile(uid = uid, nombre = nombre, email = email, rol = rol)

                firestore.collection("users").document(uid).set(perfil)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onError("Error al guardar perfil: ${it.localizedMessage}") }
            }
            .addOnFailureListener { onError(it.localizedMessage ?: "Error al registrar") }
    }

    fun isUserLoggedIn(): Boolean = auth.currentUser != null

    fun logout(onLoggedOut: () -> Unit) {
        auth.signOut()
        onLoggedOut()
    }
}
