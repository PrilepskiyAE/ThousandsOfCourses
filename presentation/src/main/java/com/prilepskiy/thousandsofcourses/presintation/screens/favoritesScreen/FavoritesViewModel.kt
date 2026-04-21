package com.prilepskiy.thousandsofcourses.presintation.screens.favoritesScreen

import androidx.lifecycle.viewModelScope
import com.prilepskiy.thousandsofcourses.common.MviBaseViewModel
import com.prilepskiy.thousandsofcourses.common.Reducer
import com.prilepskiy.thousandsofcourses.domain.usecase.GetLikeListOfUserCoursesUseCase
import com.prilepskiy.thousandsofcourses.domain.usecase.LikeDislikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    favoriteReducer: FavoritesReducer,
    private val likeDislikeUseCase: LikeDislikeUseCase,
    private val getLikesUseCases: GetLikeListOfUserCoursesUseCase
) :
    MviBaseViewModel<FavoritesState, FavoritesAction, FavoritesIntent>() {
    init {
        viewModelScope.launch {
            getLikesUseCases.invoke().onRight {
                onAction(FavoritesAction.GetFavoritesCourses(coursesList = it))
            }.onLeft {
                onAction(FavoritesAction.OnError(it))
            }
        }
    }


    override var reducer: Reducer<FavoritesAction, FavoritesState> = favoriteReducer
    override fun initState(): FavoritesState = FavoritesState()
    override fun handleIntent(intent: FavoritesIntent) {
        when (intent) {
            is FavoritesIntent.OnError -> onAction(FavoritesAction.OnError(intent.error))
            is FavoritesIntent.OnLoading -> onAction(FavoritesAction.OnLoading(intent.isLoading))
            is FavoritesIntent.OnClickLikeDislikeButton -> {
                viewModelScope.launch {
                    likeDislikeUseCase.invoke(intent.courseModel)
                    val temp = viewState.coursesList.toMutableList()
                    val courseIndex = temp.indexOfFirst { it.id == intent.courseModel.id }
                    if (courseIndex != -1) {
                        temp.removeAt(courseIndex)
                    }
                    onAction(FavoritesAction.GetFavoritesCourses(coursesList = temp))

                }
            }
        }

    }


}