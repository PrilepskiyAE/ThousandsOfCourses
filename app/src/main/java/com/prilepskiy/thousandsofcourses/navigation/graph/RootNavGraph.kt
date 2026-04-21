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
import com.prilepskiy.thousandsofcourses.presintation.screens.courseDetail.CourseDetailRoute
import com.prilepskiy.thousandsofcourses.presintation.screens.favoritesScreen.FavoriteRoute
import com.prilepskiy.thousandsofcourses.presintation.screens.homeScreen.HomeRoute
import com.prilepskiy.thousandsofcourses.presintation.screens.loginScreen.LoginRoute
import com.prilepskiy.thousandsofcourses.presintation.screens.profileScreen.ProfileRoute

@Composable
fun RootNavGraph(
    startDestination: String = AppScreens.LoginScreen.route
) {
    val rootNavController: NavHostController = rememberNavController()
    val currentBackStack by rootNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    val isBottomBarVisible = when (currentRoute) {
        AppScreens.LoginScreen.route,null -> false
        else -> true
    }
    Scaffold(
        bottomBar = {
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
                HomeRoute(navigate = {
                    rootNavController.navigate(AppScreens.DetailCourseHome.route)
                })
            }
            composable(AppScreens.Favorites.route) {
                FavoriteRoute(navigate = { rootNavController.navigate(AppScreens.DetailCourseFavorite.route) })

            }
            composable(AppScreens.Profile.route) {
                ProfileRoute() {
                    rootNavController.navigate(AppScreens.DetailCourseProfile.route)
                }
            }
            composable(AppScreens.LoginScreen.route) {
                LoginRoute(navigate={ rootNavController.navigate(AppScreens.Home.route){
                    popUpTo(rootNavController.graph.startDestinationId) {
                        inclusive = true
                    }
                } })
            }

            composable(AppScreens.DetailCourseHome.route) {
                CourseDetailRoute() {}
            }

            composable(AppScreens.DetailCourseProfile.route) {
                CourseDetailRoute() {}
            }

            composable(AppScreens.DetailCourseFavorite.route) {
                CourseDetailRoute() {}
            }

        }
    }
}