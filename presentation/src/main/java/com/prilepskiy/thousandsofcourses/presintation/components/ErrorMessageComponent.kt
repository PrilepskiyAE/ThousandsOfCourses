package com.prilepskiy.thousandsofcourses.presintation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.prilepskiy.thousandsofcourses.common.Spaces
import com.prilepskiy.thousandsofcourses.presintation.R

@Composable
fun ErrorMessageComponent(textError: String, onClickClear: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(modifier = Modifier, text = textError)
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spaces.space16), onClick = onClickClear
            ) {
                Text(stringResource(R.string.button_repite))
            }
        }
    }
}