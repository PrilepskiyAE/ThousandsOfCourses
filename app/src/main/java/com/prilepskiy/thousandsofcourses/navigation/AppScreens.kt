package com.prilepskiy.thousandsofcourses.navigation

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Stable
@Serializable
sealed class AppScreens(val route: String,val list: List<String> = listOf()) : AppNavigation {
    @Stable
    @Serializable
    data object Home : AppScreens("Home")

    @Stable
    @Serializable
    data object Favorites : AppScreens("Favorite")

    @Stable
    @Serializable
    data object Profile : AppScreens("Profile")

    @Stable
    @Serializable
    data object LoginScreen : AppScreens("LoginScreen")

    @Stable
    @Serializable
    data object  DetailCourseHome : AppScreens("Home/DetailCourse")

    @Stable
    @Serializable
    data object  DetailCourseFavorite : AppScreens("Favorite/DetailCourse")

    @Stable
    @Serializable
    data object  DetailCourseProfile : AppScreens("Profile/DetailCourse")


}
