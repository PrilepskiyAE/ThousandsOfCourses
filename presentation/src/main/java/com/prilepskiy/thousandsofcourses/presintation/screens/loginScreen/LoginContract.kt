package com.prilepskiy.thousandsofcourses.presintation.screens.loginScreen

import androidx.compose.runtime.Immutable
import com.prilepskiy.thousandsofcourses.common.MviAction
import com.prilepskiy.thousandsofcourses.common.MviIntent
import com.prilepskiy.thousandsofcourses.common.MviState
import yads.f

sealed class LoginIntent : MviIntent {
    data object OnClickLogin : LoginIntent()
    data object OnClickSignUp : LoginIntent()
    data object OnClickForgotPassword : LoginIntent()
    data class OnError(val error: String?) : LoginIntent()
    data class OnLoading(val isLoading: Boolean) : LoginIntent()
    data class OnChangePassword(val password: String) : LoginIntent()
    data class OnChangeEmail(val email: String) : LoginIntent()


}

sealed class LoginAction : MviAction {
    data class OnError(val error: String?) : LoginAction()
    data class OnLoading(val isLoading: Boolean) : LoginAction()
    data class OnChangePassword(val password: String, val validPassword: Boolean) : LoginAction()
    data class OnChangeEmail(val email: String, val validEmail: Boolean) : LoginAction()

    data object OnClickLogin : LoginAction()
}


@Immutable
data class LoginState(
    override val error: String? = null,
    override val isLoading: Boolean = true,
    val password: String = "",
    val validPassword: Boolean? = null,
    val email: String = "",
    val validEmail: Boolean? = null,
    val loginSuccessfull: Boolean = false
) : MviState