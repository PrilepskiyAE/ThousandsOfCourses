package com.prilepskiy.thousandsofcourses.presintation.screens.favoritesScreen

import androidx.compose.runtime.Immutable
import com.prilepskiy.thousandsofcourses.common.MviAction
import com.prilepskiy.thousandsofcourses.common.MviIntent
import com.prilepskiy.thousandsofcourses.common.MviState
import com.prilepskiy.thousandsofcourses.domain.model.CourseModel
import com.prilepskiy.thousandsofcourses.presintation.screens.homeScreen.HomeIntent

sealed class FavoritesIntent : MviIntent {
    data class OnError(val error: String?) : FavoritesIntent()
    data class OnLoading(val isLoading: Boolean) : FavoritesIntent()
    data class OnClickLikeDislikeButton(val courseModel: CourseModel) : FavoritesIntent()

}

sealed class FavoritesAction : MviAction {
    data class GetFavoritesCourses(val coursesList: List<CourseModel>) : FavoritesAction()
    data class OnError(val error: String?) : FavoritesAction()
    data class OnLoading(val isLoading: Boolean) : FavoritesAction()
}

@Immutable
data class FavoritesState(
    val coursesList: List<CourseModel> = listOf(),
    override val error: String? = null,
    override val isLoading: Boolean = true
) : MviState