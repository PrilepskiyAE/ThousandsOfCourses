package com.prilepskiy.thousandsofcourses.presintation.screens.loginScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.prilepskiy.thousandsofcourses.common.GreenColor
import com.prilepskiy.thousandsofcourses.common.Sizes
import com.prilepskiy.thousandsofcourses.common.Spaces
import com.prilepskiy.thousandsofcourses.common.WhiteColor
import com.prilepskiy.thousandsofcourses.presintation.R
import com.prilepskiy.thousandsofcourses.presintation.components.BaseTextFieldComponent
import com.prilepskiy.thousandsofcourses.presintation.components.LoginComponent

@Composable
fun LoginScreen(navigate: () -> Unit) {

    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spaces.space14),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.clickable(onClick = { navigate.invoke() }),
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
            onValueChangeEmail = { email -> emailText = email },
            onValueChangePassword = { password -> passwordText = password },
            emailErrorMessage = null,
            passwordErrorMessage = null
        )

        Spacer(modifier = Modifier.height(Spaces.space24))
        Button(
            onClick = { /* действие при нажатии */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(Spaces.space40)
                .alpha(1f),
            shape = RoundedCornerShape(Spaces.space30),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenColor,
                contentColor = Color.White
            ),

            ) {
            Text(
                text = stringResource(R.string.login_screen_button_login),
                color = Color.White,
                fontSize = Sizes.size14
            )
        }


    }
}
