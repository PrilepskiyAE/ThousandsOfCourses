package com.prilepskiy.thousandsofcourses.presintation.screens.homeScreen

import com.prilepskiy.thousandsofcourses.common.Reducer
import javax.inject.Inject

class HomeReducer @Inject constructor() : Reducer<HomeAction, HomeState> {
    override fun reduce(
        action: HomeAction,
        state: HomeState
    ): HomeState = when (action) {
        is HomeAction.OnError -> state.copy(error = action.error, isLoading = false)
        is HomeAction.OnLoading -> state.copy(isLoading = action.isLoading)
        is HomeAction.SetCourses -> state.copy(
            coursesList = action.coursesModel,
            isLoading = false,
            isSortAsc = action.isSortAsc
        )

        is HomeAction.OnSearchChanged -> state.copy(query = action.query)
    }
}
