package com.prilepskiy.thousandsofcourses.presintation.screens.favoritesScreen

import com.prilepskiy.thousandsofcourses.common.Reducer
import javax.inject.Inject

class FavoritesReducer @Inject constructor() : Reducer<FavoritesAction, FavoritesState> {
    override fun reduce(
        action: FavoritesAction,
        state: FavoritesState
    ): FavoritesState = when (action) {
        is FavoritesAction.OnError -> state.copy(error = action.error, isLoading = false)
        is FavoritesAction.OnLoading -> state.copy(isLoading = action.isLoading)
        is FavoritesAction.GetFavoritesCourses -> state.copy(
            coursesList = action.coursesList,
            isLoading = false
        )
    }
}