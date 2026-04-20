package com.prilepskiy.thousandsofcourses.presintation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.prilepskiy.thousandsofcourses.common.RedErrorColor
import com.prilepskiy.thousandsofcourses.common.Sizes
import com.prilepskiy.thousandsofcourses.common.Spaces
import com.prilepskiy.thousandsofcourses.common.WhiteColor
import com.prilepskiy.thousandsofcourses.presintation.R

@Composable
fun LoginComponent(
    modifier: Modifier=Modifier,
    emailText: String,
    onValueChangeEmail: (String) -> Unit,
    passwordText: String,
    onValueChangePassword: (String) -> Unit,
    validEmail : Boolean? ,
    validPasswrod :Boolean?,

) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    Column(modifier) {
        Text(
            text = stringResource(R.string.login_screen_email_label),
            color = WhiteColor,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W500,
                fontStyle = FontStyle.Normal,
                fontSize = Sizes.size16,
                lineHeight = Sizes.size18,
                letterSpacing = Sizes.size05
            )
        )

        BaseTextFieldComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Spaces.space6)
                .height(Spaces.space40),

            valueText = emailText,
            onValueChange = onValueChangeEmail,
            placeholderText = stringResource(R.string.login_screen_email_placeholder),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )

        if (validEmail ==false) {
            Text(
                text = stringResource(R.string.email_error),
                color = RedErrorColor,
            )
        }

        Spacer(modifier = Modifier.height(Spaces.space24))
        Text(
            text = stringResource(R.string.login_screen_password_label),
            color = WhiteColor,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W500,
                fontStyle = FontStyle.Normal,
                fontSize = Sizes.size16,
                lineHeight = Sizes.size18,
                letterSpacing = Sizes.size05
            )
        )

        BaseTextFieldComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Spaces.space6)
                .height(Spaces.space40),

            valueText = passwordText,
            onValueChange = onValueChangePassword,
            placeholderText = stringResource(R.string.login_screen_password_plaseholder),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

            trailingIcon = {
                if (passwordText.isNotEmpty()) {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Text(
                            text = if (isPasswordVisible) "👁️‍🗨" else "👁️",
                            fontSize = Sizes.size24
                        )
                    }
                }

            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        if (validPasswrod == false) {
            Text(
                text = stringResource(R.string.passwrod_error),
                color = RedErrorColor,
            )
        }
    }
}