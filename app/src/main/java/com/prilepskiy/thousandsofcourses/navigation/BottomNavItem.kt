package com.prilepskiy.thousandsofcourses.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(
    val nav:  AppScreens,
    val icon: ImageVector,
    val title: String
) {
    Courses(AppScreens.Home, Icons.Outlined.Home, "Home"),
    Favorites(AppScreens.Favorites, Icons.Outlined.Favorite, "Favorites"),
    Profile(AppScreens.Profile, Icons.Outlined.Person, "Profile")
}