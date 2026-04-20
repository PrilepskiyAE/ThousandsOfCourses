package com.prilepskiy.thousandsofcourses.presintation.screens.loginScreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.thousandsofcourses.common.MviBaseViewModel
import com.prilepskiy.thousandsofcourses.common.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(loginReducer: LoginReducer, ): MviBaseViewModel<LoginState, LoginAction, LoginIntent>() {
    override var reducer: Reducer<LoginAction, LoginState> = loginReducer
    override fun initState(): LoginState = LoginState()
    init {
        viewModelScope.launch {
            delay(500)
            onAction(LoginAction.OnLoading(false))
        }
    }
    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.OnChangeEmail -> {

                fun isValidEmail(email: String): Boolean {
                    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
                    return emailRegex.matches(email)
                }
                onAction(LoginAction.OnChangeEmail(email= intent.email,isValidEmail(intent.email) ))
            }
            is LoginIntent.OnChangePassword -> onAction(LoginAction.OnChangePassword(password = intent.password, validPassword = intent.password.isNotBlank() ))
            LoginIntent.OnClickForgotPassword -> {
                //TODO implement forgot password logic here
                Log.d(TAG_LOGIN, "handleIntent: OnClickForgotPasswor")
            }
            LoginIntent.OnClickLogin -> {
                if (viewState.validPassword == true && viewState.validEmail == true){
                    onAction(LoginAction.OnClickLogin)
                }

            }
            LoginIntent.OnClickSignUp -> {
                Log.d(TAG_LOGIN, "handleIntent: OnClickSignUp ")
            }
            is LoginIntent.OnError -> onAction(LoginAction.OnError(intent.error))
            is LoginIntent.OnLoading -> onAction(LoginAction.OnLoading(intent.isLoading))
        }
    }
    companion object{
        const val TAG_LOGIN="LOGIN_VIEW_MODEL"
    }
}