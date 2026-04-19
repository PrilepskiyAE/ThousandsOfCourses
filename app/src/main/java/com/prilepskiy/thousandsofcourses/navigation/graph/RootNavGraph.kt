package com.prilepskiy.thousandsofcourses.navigation.graph

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.prilepskiy.thousandsofcourses.navigation.AppScreens
import com.prilepskiy.thousandsofcourses.navigation.BottomNavigationBar
import com.prilepskiy.thousandsofcourses.presintation.screens.courseDetail.CourseDetailScreen
import com.prilepskiy.thousandsofcourses.presintation.screens.courseScreen.CourseScreen
import com.prilepskiy.thousandsofcourses.presintation.screens.favoritesScreen.FavoritesScreen
import com.prilepskiy.thousandsofcourses.presintation.screens.loginScreen.LoginScreen
import com.prilepskiy.thousandsofcourses.presintation.screens.profileScreen.ProfileScreen

@Composable
fun RootNavGraph(
    startDestination: String = AppScreens.LoginScreen.route
) {
    val rootNavController: NavHostController = rememberNavController()
    val currentBackStack by rootNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    val isBottomBarVisible = when (currentRoute) {
        AppScreens.LoginScreen.route -> false
        else -> true
    }
    Scaffold(
        bottomBar = {
         //   SetNavigationBarColor(isBottomBarVisible)
            if (isBottomBarVisible)
                BottomNavigationBar(navController = rootNavController)
        }
    ) { paddingValues ->
        NavHost(
            navController = rootNavController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(AppScreens.Home.route) {
                CourseScreen() {
                    rootNavController.navigate(AppScreens.DetailCourseHome.route)
                }
            }
            composable(AppScreens.Favorites.route) {
                FavoritesScreen() {
                    rootNavController.navigate(AppScreens.DetailCourseFavorite.route)
                }
            }
            composable(AppScreens.Profile.route) {
                ProfileScreen() {
                    rootNavController.navigate(AppScreens.DetailCourseProfile.route)
                }
            }
            composable(AppScreens.LoginScreen.route) {
                LoginScreen() { rootNavController.navigate(AppScreens.Home.route) }
            }

            composable(AppScreens.DetailCourseHome.route) {
                CourseDetailScreen() {}
            }

            composable(AppScreens.DetailCourseProfile.route) {
                CourseDetailScreen() {}
            }

            composable(AppScreens.DetailCourseFavorite.route) {
                CourseDetailScreen() {}
            }

        }
    }
}