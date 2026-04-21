package com.prilepskiy.thousandsofcourses.presintation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import com.prilepskiy.thousandsofcourses.common.LightGrayColor
import com.prilepskiy.thousandsofcourses.common.Sizes
import com.prilepskiy.thousandsofcourses.common.Spaces
import com.prilepskiy.thousandsofcourses.common.WhiteColor


@Composable
fun BaseTextFieldComponent(
    modifier: Modifier = Modifier,
    valueText: String,
    onValueChange: (String) -> Unit,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    fontSize: TextUnit = Sizes.size14,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    backgroundColor: Color = LightGrayColor
) {

    BasicTextField(
        modifier = modifier
            .background(
                SolidColor(backgroundColor),
                RoundedCornerShape(Spaces.space32),
            )
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        value = valueText,
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        cursorBrush = SolidColor(WhiteColor),
        textStyle = LocalTextStyle.current.copy(
            color = WhiteColor,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()

                Box(Modifier
                    .weight(1f)
                    .padding(horizontal = Spaces.space16)) {
                    if (valueText.isEmpty()) {
                        Text(
                            //modifier = Modifier.padding(horizontal = Spaces.space16),
                            text = placeholderText,
                            style = LocalTextStyle.current.copy(
                                color = WhiteColor.copy(alpha = 0.3f),
                                fontSize = fontSize
                            )
                        )
                    }

                    innerTextField()

                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}
