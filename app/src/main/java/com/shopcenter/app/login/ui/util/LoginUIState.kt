package com.shopcenter.app.login.ui.util

import com.shopcenter.app.login.data.UserG

sealed interface LoginUIState{

    val isLoading: Boolean
    val errorMessage: String
    val isSuccessful: Boolean

    data class NoUser(
        override val isLoading: Boolean,
        override val errorMessage: String,
        override val isSuccessful: Boolean
    ): LoginUIState

    data class User(
        val dataUser : UserG?,
        override val isLoading: Boolean,
        override val errorMessage: String,
        override val isSuccessful: Boolean
    ): LoginUIState
}