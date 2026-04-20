package com.prilepskiy.thousandsofcourses.presintation.screens.loginScreen

import com.prilepskiy.thousandsofcourses.common.Reducer
import javax.inject.Inject

class LoginReducer @Inject constructor() : Reducer<LoginAction, LoginState> {
    override fun reduce(
        action: LoginAction,
        state: LoginState
    ): LoginState =
        when (action) {
            is LoginAction.OnChangeEmail -> state.copy(
                email = action.email,
                validEmail = action.validEmail
            )

            is LoginAction.OnChangePassword -> state.copy(
                password = action.password,
                validPassword = action.validPassword
            )

            is LoginAction.OnError -> state.copy(error = action.error, isLoading = false)
            is LoginAction.OnLoading -> state.copy(isLoading = action.isLoading)
            LoginAction.OnClickLogin -> state.copy(loginSuccessfull = true)
        }
}