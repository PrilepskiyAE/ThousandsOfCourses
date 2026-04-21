package com.prilepskiy.thousandsofcourses.presintation.screens.homeScreen

import androidx.lifecycle.viewModelScope
import com.prilepskiy.thousandsofcourses.common.MviBaseViewModel
import com.prilepskiy.thousandsofcourses.common.Reducer
import com.prilepskiy.thousandsofcourses.domain.usecase.GetCoursesSortedByPublishDateAscUseCase
import com.prilepskiy.thousandsofcourses.domain.usecase.GetCoursesSortedByPublishDateDescUseCase
import com.prilepskiy.thousandsofcourses.domain.usecase.GetCoursesUseCase
import com.prilepskiy.thousandsofcourses.domain.usecase.LikeDislikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeReducer: HomeReducer,
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getCoursesSortedByPublishDateAscUseCase: GetCoursesSortedByPublishDateAscUseCase,
    private val getCoursesSortedByPublishDateDescUseCase: GetCoursesSortedByPublishDateDescUseCase,
    private val likeDislikeUseCase: LikeDislikeUseCase,

) : MviBaseViewModel<HomeState, HomeAction, HomeIntent>() {
    override var reducer: Reducer<HomeAction, HomeState> = homeReducer
    override fun initState(): HomeState = HomeState()

    init {
        viewModelScope.launch {
            getCoursesUseCase.invoke().onRight {
                onAction(HomeAction.SetCourses(coursesModel = it,isSortAsc = false))
            }.onLeft {
                onAction(HomeAction.OnError(it))
            }
        }
    }

    override fun handleIntent(intent: HomeIntent) {
        when (intent){

            is HomeIntent.OnClick -> {

            }
            HomeIntent.OnClickFilterButton -> {

            }
            is HomeIntent.OnClickLikeDislikeButton -> {
                val temp = viewState.coursesList.toMutableList()
                val courseIndex = temp.indexOfFirst { it.id == intent.courseModel.id }
                if (courseIndex != -1) {
                    temp[courseIndex] = temp[courseIndex].copy(
                        hasLike = !temp[courseIndex].hasLike
                    )
                }
                onAction(HomeAction.SetCourses(coursesModel = temp, isSortAsc = viewState.isSortAsc))
                viewModelScope.launch {  likeDislikeUseCase.invoke(intent.courseModel) }

            }
            HomeIntent.OnClickSortButton -> {
                if (!viewState.isSortAsc){
                    getCoursesSortedByPublishDateAsc()
                }else{
                    getCoursesSortedByPublishDateDesc()
                }
            }
            is HomeIntent.OnError -> onAction(HomeAction.OnError(intent.error))
            is HomeIntent.OnLoading -> onAction(HomeAction.OnLoading(intent.isLoading))
            is HomeIntent.OnSearchChanged -> onAction(HomeAction.OnSearchChanged(intent.query))
        }
    }
    private fun getCoursesSortedByPublishDateAsc() {
        viewModelScope.launch {
            getCoursesSortedByPublishDateAscUseCase.invoke().onRight {
                onAction(HomeAction.SetCourses(it,true))
            }.onLeft {
                onAction(HomeAction.OnError(it))
            }
        }
    }
    private fun getCoursesSortedByPublishDateDesc(){
        viewModelScope.launch {
            getCoursesSortedByPublishDateDescUseCase.invoke().onRight {
                onAction(HomeAction.SetCourses(it,false))
            }.onLeft {
                onAction(HomeAction.OnError(it))
            }
        }
    }

}