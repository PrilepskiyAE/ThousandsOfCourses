package com.prilepskiy.thousandsofcourses.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.prilepskiy.thousandsofcourses.R

enum class BottomNavItem(
    val nav:  AppScreens,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
) {
    Courses(AppScreens.Home, R.drawable.ic_home, R.string.page_home),
    Favorites(AppScreens.Favorites, R.drawable.ic_favorite, R.string.page_favorite),
    Profile(AppScreens.Profile, R.drawable.ic_profile, R.string.page_profile)
}