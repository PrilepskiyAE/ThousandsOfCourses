package com.prilepskiy.thousandsofcourses.navigation

import android.util.Log
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.prilepskiy.thousandsofcourses.common.Sizes
import com.prilepskiy.thousandsofcourses.common.Spaces
import com.prilepskiy.thousandsofcourses.ui.theme.PurpleGrey40

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentRoute = currentRoute(navController)
    HorizontalDivider(
        thickness = Spaces.space2,
        color = PurpleGrey40
    )
    NavigationBar(containerColor=Color.Black, contentColor = Color.White) {
        BottomNavItem.values().forEach { item ->

            val selected = currentRoute?.startsWith(item.nav.route) == true
            NavigationBarItem(
                icon = {
                    Icon(item.icon, contentDescription = item.title)
                },
                label = { Text(item.title) },
                selected = selected,
                onClick = {
                    navController.navigate(item.nav.route) {

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = true
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val route = navBackStackEntry?.destination?.route
    Log.d("BottomNav", "Current route: $route")
    return route
}