package com.prilepskiy.thousandsofcourses.presintation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.prilepskiy.thousandsofcourses.common.DarkGreenColor
import com.prilepskiy.thousandsofcourses.presintation.R

@Composable
fun SortComponent(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(

    ) {
        Spacer(Modifier.weight(1f));
        Row(modifier = modifier.clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(R.string.soft_component_button_sort), color = DarkGreenColor)
            Icon(painterResource(R.drawable.ic_arrow_down_up), "", tint = DarkGreenColor)
        }
        }

}