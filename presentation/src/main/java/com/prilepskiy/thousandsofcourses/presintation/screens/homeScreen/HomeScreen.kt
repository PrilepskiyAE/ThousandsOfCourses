package com.prilepskiy.thousandsofcourses.presintation.screens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.prilepskiy.thousandsofcourses.common.Spaces
import com.prilepskiy.thousandsofcourses.domain.model.CourseModel
import com.prilepskiy.thousandsofcourses.presintation.components.CourseListComponent
import com.prilepskiy.thousandsofcourses.presintation.components.ErrorMessageComponent
import com.prilepskiy.thousandsofcourses.presintation.components.LoadingComponent
import com.prilepskiy.thousandsofcourses.presintation.components.SearchComponent
import com.prilepskiy.thousandsofcourses.presintation.components.SortComponent

@Composable
fun HomeRoute(navigate: () -> Unit, viewModel: HomeViewModel = hiltViewModel()) {
    val state = viewModel.viewState

    if (state.isLoading) {
        LoadingComponent()
    } else if (!state.error.isNullOrEmpty()) {
        ErrorMessageComponent(textError = state.error) {
        }
    } else {
        HomeScreen(
            list = state.coursesList,
            searchText = state.query,
            onSearchChange = { viewModel.onIntent(HomeIntent.OnSearchChanged(it)) },
            onClickSort = { viewModel.onIntent(HomeIntent.OnClickSortButton) },
            onItemClick = { navigate.invoke() },
            onFilteClick = { viewModel.onIntent(HomeIntent.OnClickFilterButton) },
            onClickFavoriteItem = { viewModel.onIntent(HomeIntent.OnClickLikeDislikeButton(it)) }
        )
    }
}


@Composable
private fun HomeScreen(
    list: List<CourseModel>,
    searchText: String,
    onSearchChange: (String) -> Unit,
    onClickSort: () -> Unit,
    onItemClick: (CourseModel) -> Unit,
    onFilteClick: () -> Unit,
    onClickFavoriteItem: (CourseModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spaces.space14),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchComponent(modifier = Modifier, searchText, onSearchChange, onFilteClick)
        SortComponent() { onClickSort.invoke() }
        CourseListComponent(
            list = list,
            onItemClick = onItemClick,
            onClickFavoriteItem = onClickFavoriteItem
        )
    }
}