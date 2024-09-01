package com.shopcenter.app.login.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.shopcenter.app.login.data.UserG
import com.shopcenter.app.login.ui.util.LoginViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val viewModelState = MutableStateFlow(LoginViewModelState(isLoading = true))

    val uiSate = viewModelState
        .map(LoginViewModelState::toLoginUIState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toLoginUIState()
        )


    fun firebaseAuthWithGoogle(credential: AuthCredential) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModelState.update {
                        it.copy(
                            dataUser = UserG(
                                displayName = auth.currentUser?.displayName,
                                email = auth.currentUser?.email,
                                photoUrl = auth.currentUser?.photoUrl.toString()
                            ),
                            isSuccessful = true,
                            isLoading = false
                        )
                    }

                    Log.d("logViewModel", "firebaseAuthWithGoogle: ${auth.currentUser?.displayName}")
                } else {
                    viewModelState.update {
                        it.copy(
                            isSuccessful = false,
                            isLoading = false,
                            errorMessage = task.exception?.message.toString()
                        )
                    }
                    Log.d("logViewModelError", "firebaseAuthWithGoogle: ${auth.currentUser?.displayName}")

                }
            }
    }


}