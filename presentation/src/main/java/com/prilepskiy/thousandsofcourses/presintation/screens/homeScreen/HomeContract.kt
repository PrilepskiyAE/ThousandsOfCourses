package com.prilepskiy.thousandsofcourses.presintation.screens.homeScreen

import androidx.compose.runtime.Immutable
import com.prilepskiy.thousandsofcourses.common.MviAction
import com.prilepskiy.thousandsofcourses.common.MviIntent
import com.prilepskiy.thousandsofcourses.common.MviState
import com.prilepskiy.thousandsofcourses.domain.model.CourseModel

sealed class HomeIntent : MviIntent {
    data class OnError(val error: String?) : HomeIntent()
    data class OnLoading(val isLoading: Boolean) : HomeIntent()
    data class OnClickLikeDislikeButton(val courseModel: CourseModel) : HomeIntent()
    data object OnClickFilterButton: HomeIntent()
    data object OnClickSortButton: HomeIntent()
    data class OnSearchChanged (val query:String ): HomeIntent()
    data class OnClick (val courseModel:CourseModel ): HomeIntent()
}

sealed class HomeAction : MviAction {
    data class OnError(val error: String?) : HomeAction()
    data class OnLoading(val isLoading: Boolean) : HomeAction()
    data class OnSearchChanged (val query:String ): HomeAction()
    data class SetCourses(val coursesModel: List<CourseModel>,val isSortAsc: Boolean) : HomeAction()
}

@Immutable
data class HomeState(
    val coursesList: List<CourseModel> = listOf(),
    override val error: String? = null,
    override val isLoading: Boolean = true,
    val isSortAsc: Boolean=false,
    val query:String=""
) : MviState