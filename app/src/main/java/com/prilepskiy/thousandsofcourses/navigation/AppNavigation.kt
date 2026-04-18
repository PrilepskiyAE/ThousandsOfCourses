package com.prilepskiy.thousandsofcourses.navigation

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Stable
interface  AppNavigation {


    @Stable
    @Serializable
    data class Back(
        val payload: Map<String, String>? = null
    ) : AppNavigation

    @Stable
    @Serializable
    data class BackTo(
        val screen: AppNavigation,
        val exclusive:Boolean = false,
        val payload: Map<String, String>? = null
    ) : AppNavigation

    @Stable
    @Serializable
    data class NavigateToRoot(
        val screen: AppNavigation,
        val exclusive:Boolean = false
    ) : AppNavigation
}

