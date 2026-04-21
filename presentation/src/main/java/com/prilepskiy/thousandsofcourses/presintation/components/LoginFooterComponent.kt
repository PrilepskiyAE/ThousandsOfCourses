package com.prilepskiy.thousandsofcourses.presintation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.prilepskiy.thousandsofcourses.common.DarkGreenColor
import com.prilepskiy.thousandsofcourses.common.Sizes
import com.prilepskiy.thousandsofcourses.common.Spaces
import com.prilepskiy.thousandsofcourses.common.WhiteColor
import com.prilepskiy.thousandsofcourses.presintation.R

@Composable
fun LoginFooterComponent(
    modifier: Modifier = Modifier,
    forgotPasswordButton: () -> Unit,
    signUpButton: () -> Unit,
) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.padding(vertical = Spaces.space16)) {
            Text(
                stringResource(R.string.login_footer_component_text),
                color = WhiteColor,
                fontSize = Sizes.size12,
                lineHeight = Sizes.size15,
                letterSpacing = Sizes.size05,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W600,
            )
            Text(
                modifier = Modifier.clickable(onClick = { signUpButton() }),
                text = stringResource(R.string.login_footer_component_button_1),
                color = DarkGreenColor,
                fontSize = Sizes.size12,
                lineHeight = Sizes.size15,
                letterSpacing = Sizes.size04,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W600,
            )
        }
        Text(
            modifier = Modifier.clickable(onClick = { forgotPasswordButton() }),
            text = stringResource(R.string.login_footer_component_button_2),
            color = DarkGreenColor,
            fontSize = Sizes.size12,
            lineHeight = Sizes.size15,
            letterSpacing = Sizes.size04,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W600,
        )
    }
}