package com.example.quickStock.auth

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.ClearCredentialException
import androidx.credentials.exceptions.GetCredentialException
import com.example.quickStock.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val credentialManager = CredentialManager.create(context)

    suspend fun launchCredentialManager(
        onSuccess: (String, String) -> Unit,
        onError: (String) -> Unit
    ) {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(context.getString(R.string.google_server_client_id))
            .setFilterByAuthorizedAccounts(false)
            .build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        try {
            val result = credentialManager.getCredential(
                context = context,
                request = request
            )
            handleSignIn(result.credential, onSuccess, onError)
        } catch (e: GetCredentialException) {
            onError("No se pudo obtener las credenciales: ${e.localizedMessage}")
        }
    }

    private fun handleSignIn(
        credential: Credential,
        onSuccess: (String, String) -> Unit,
        onError: (String) -> Unit
    ) {
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            firebaseAuthWithGoogle(googleIdTokenCredential.idToken, onSuccess, onError)
        } else {
            onError("La credencial no es de tipo Google ID")
        }
    }

    private fun firebaseAuthWithGoogle(
        idToken: String,
        onSuccess: (String, String) -> Unit,
        onError: (String) -> Unit
    ) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val name = user?.displayName ?: ""
                    val email = user?.email ?: ""
                    onSuccess(name, email)

                } else {
                    onError(task.exception?.message ?: "Error de autenticación")
                }
            }
    }

    fun getCurrentUserInfo(): Pair<String, String> {
        val user = firebaseAuth.currentUser
        val name = user?.displayName ?: ""
        val email = user?.email ?: ""
        return Pair(name, email)
    }

    suspend fun signOut(onComplete: (() -> Unit)? = null) {
        firebaseAuth.signOut()
        // Limpia el estado de credenciales
        try {
            val clearRequest = ClearCredentialStateRequest()
            credentialManager.clearCredentialState(clearRequest)
            onComplete?.invoke()
        } catch (e: ClearCredentialException) {
            onComplete?.invoke()
        }
    }
}
