package com.prilepskiy.thousandsofcourses.presintation.screens.loginScreen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.prilepskiy.thousandsofcourses.common.GreenColor
import com.prilepskiy.thousandsofcourses.common.Sizes
import com.prilepskiy.thousandsofcourses.common.Spaces
import com.prilepskiy.thousandsofcourses.common.StrokeColor
import com.prilepskiy.thousandsofcourses.common.WhiteColor
import com.prilepskiy.thousandsofcourses.presintation.R
import com.prilepskiy.thousandsofcourses.presintation.components.ErrorMessageComponent
import com.prilepskiy.thousandsofcourses.presintation.components.LoadingComponent
import com.prilepskiy.thousandsofcourses.presintation.components.LoginComponent
import com.prilepskiy.thousandsofcourses.presintation.components.LoginFooterComponent
import com.prilepskiy.thousandsofcourses.presintation.components.SocialNetworkComponent

@Composable
fun LoginRoute(navigate: () -> Unit, viewModel: LoginViewModel = hiltViewModel()) {
    val state = viewModel.viewState

    LaunchedEffect(state.loginSuccessfull) {
        if (state.loginSuccessfull) {
            navigate.invoke()
        }
    }

    if (state.isLoading) {
        LoadingComponent()
    } else if (!state.error.isNullOrEmpty()) {
        ErrorMessageComponent(textError = state.error) {
        }
    } else {
        LoginScreen(
            emailText = state.email,
            passwordText = state.password,
            validEmail = state.validEmail,
            validPasswrod = state.validPassword,
            onValueChangeEmail = { viewModel.onIntent(LoginIntent.OnChangeEmail(it)) },
            onValueChangePassword = { viewModel.onIntent(LoginIntent.OnChangePassword(it)) },
            onClickLogin = { viewModel.onIntent(LoginIntent.OnClickLogin) },
            onClickForgotPassword = { viewModel.onIntent(LoginIntent.OnClickForgotPassword) },
            onClickSignUp = { viewModel.onIntent(LoginIntent.OnClickSignUp) }
        )
    }
}

@Composable
private fun LoginScreen(
    emailText: String,
    passwordText: String,
    validEmail: Boolean?,
    validPasswrod: Boolean?,
    onValueChangeEmail: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
    onClickLogin: () -> Unit,
    onClickForgotPassword: () -> Unit,
    onClickSignUp: () -> Unit
) {

    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(Spaces.space14),

        ) {
        Text(
            text = stringResource(R.string.login_screen_title),
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400,
                fontStyle = FontStyle.Normal,
                fontSize = Sizes.size28,
                lineHeight = Sizes.size36,
                letterSpacing = Sizes.size0
            )
        )
        Spacer(modifier = Modifier.height(Spaces.space64))

        LoginComponent(
            emailText = emailText,
            passwordText = passwordText,
            onValueChangeEmail = onValueChangeEmail,
            onValueChangePassword = onValueChangePassword,
            validEmail = validEmail,
            validPasswrod = validPasswrod
        )

        Spacer(modifier = Modifier.height(Spaces.space24))
        Button(
            enabled = validEmail == true && validPasswrod == true,
            onClick = onClickLogin,
            modifier = Modifier
                .fillMaxWidth()
                .height(Spaces.space40)
                .alpha(1f),
            shape = RoundedCornerShape(Spaces.space30),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenColor,
                contentColor = WhiteColor,
                disabledContentColor = Color.Gray,
                disabledContainerColor = GreenColor
            ),

            ) {
            Text(
                text = stringResource(R.string.login_screen_button_login),
                color = Color.White,
                fontSize = Sizes.size14
            )
        }
        Spacer(modifier = Modifier.height(Spaces.space16))
        LoginFooterComponent(
            modifier = Modifier.fillMaxWidth(),
            forgotPasswordButton = onClickForgotPassword,
            signUpButton = onClickSignUp
        )
        Spacer(modifier = Modifier.height(Spaces.space24))
        HorizontalDivider(
            thickness = Spaces.space1,
            color = StrokeColor
        )
        Spacer(modifier = Modifier.height(Spaces.space24))
        SocialNetworkComponent(
            modifier = Modifier.fillMaxWidth(),
            vkButton = { openWebSite("https://vk.com/", context) },
            okButton = { openWebSite("https://ok.ru/", context) })
    }
}

private fun openWebSite(url: String, context: Context) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        url.toUri()
    )
    context.startActivity(intent)
}