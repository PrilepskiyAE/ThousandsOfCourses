package com.prilepskiy.thousandsofcourses.presintation.screens.courseScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.prilepskiy.thousandsofcourses.common.Sizes
import com.prilepskiy.thousandsofcourses.common.Spaces

@Composable
fun CourseScreen(navigate: ()->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spaces.space14),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(modifier = Modifier.clickable(onClick = {navigate.invoke()}), text = "Course Screen", fontSize = Sizes.size24)
    }
}