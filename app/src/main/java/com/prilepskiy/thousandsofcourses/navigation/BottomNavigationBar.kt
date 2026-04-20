package com.prilepskiy.thousandsofcourses.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.prilepskiy.thousandsofcourses.common.NavBarColor
import com.prilepskiy.thousandsofcourses.common.Spaces
import com.prilepskiy.thousandsofcourses.common.StrokeColor


@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentRoute = currentRoute(navController)
    Column(){
        HorizontalDivider(
            thickness = Spaces.space2,
            color =  StrokeColor
        )
        NavigationBar(containerColor=NavBarColor, ) {
            BottomNavItem.entries.forEach { item ->

                val selected = currentRoute?.startsWith(item.nav.route) == true
                NavigationBarItem(
                    modifier = Modifier.padding(horizontal = Spaces.space24),
                    icon = {
                        Icon(
                            modifier = Modifier.size(Spaces.space64,Spaces.space32),
                            painter = painterResource(id=item.icon),
                            contentDescription = stringResource(item.title),
                            tint = if (selected) Color.Green else Color.White
                        )
                    },
                    label = { Text(text = stringResource(item.title) ,color= if (selected) Color.Green else Color.White ) },
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

}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val route = navBackStackEntry?.destination?.route
    return route
}
