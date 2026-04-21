package com.prilepskiy.thousandsofcourses.presintation.screens.favoritesScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.prilepskiy.thousandsofcourses.common.Sizes
import com.prilepskiy.thousandsofcourses.common.Spaces
import com.prilepskiy.thousandsofcourses.domain.model.CourseModel
import com.prilepskiy.thousandsofcourses.presintation.R
import com.prilepskiy.thousandsofcourses.presintation.components.CourseListComponent
import com.prilepskiy.thousandsofcourses.presintation.components.ErrorMessageComponent
import com.prilepskiy.thousandsofcourses.presintation.components.LoadingComponent

@Composable
fun FavoriteRoute(navigate: () -> Unit, viewModel: FavoritesViewModel = hiltViewModel()) {
    val state = viewModel.viewState

    if (state.isLoading) {
        LoadingComponent()
    } else if (!state.error.isNullOrEmpty()) {
        ErrorMessageComponent(textError = state.error) {
        }
    } else {
        FavoritesScreen(
            list = state.coursesList,
            onItemClick = { navigate() },
            onClickFavoriteItem = { viewModel.onIntent(FavoritesIntent.OnClickLikeDislikeButton(it)) })
    }
}

@Composable
private fun FavoritesScreen(
    list: List<CourseModel>,
    onItemClick: (CourseModel) -> Unit,
    onClickFavoriteItem: (CourseModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spaces.space14),
    ) {
        Text(
            modifier = Modifier.padding(bottom = Spaces.space8),
            text = stringResource(R.string.favorite_title),
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400,
                fontStyle = FontStyle.Normal,
                fontSize = Sizes.size28,
                lineHeight = Sizes.size36,
                letterSpacing = Sizes.size0
            )
        )
        CourseListComponent(
            list = list,
            onItemClick = onItemClick,
            onClickFavoriteItem = onClickFavoriteItem
        )
    }
}