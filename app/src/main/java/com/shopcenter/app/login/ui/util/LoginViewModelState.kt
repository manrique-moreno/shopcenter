package com.shopcenter.app.login.ui.util

import com.shopcenter.app.login.data.UserG

data class LoginViewModelState(
    val dataUser: UserG? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isSuccessful: Boolean = false,
) {
    fun toLoginUIState(): LoginUIState = if(isSuccessful){
        LoginUIState.User(
            isLoading = isLoading,
            errorMessage = errorMessage,
            isSuccessful = isSuccessful,
            dataUser = dataUser
        )
    }else{
        LoginUIState.NoUser(
            isLoading = isLoading,
            errorMessage = errorMessage,
            isSuccessful = isSuccessful
        )
    }
}