package com.prilepskiy.thousandsofcourses.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(
    val nav:  AppScreens,
    val icon: ImageVector,
    val title: String
) {
    Courses(AppScreens.Home, Icons.Filled.Home, "Home"),
    Favorites(AppScreens.Favorites, Icons.Filled.Favorite, "Favorites"),
    Profile(AppScreens.Profile, Icons.Filled.Person, "Profile")
}