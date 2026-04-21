package com.prilepskiy.thousandsofcourses.presintation.screens.courseDetail

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
fun CourseDetailRoute(navigate: () -> Unit) {
    CourseDetailScreen()
}

@Composable
private fun CourseDetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spaces.space14),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.clickable(onClick = {}),
            text = "Course Detail Screen",
            fontSize = Sizes.size24
        )
    }
}