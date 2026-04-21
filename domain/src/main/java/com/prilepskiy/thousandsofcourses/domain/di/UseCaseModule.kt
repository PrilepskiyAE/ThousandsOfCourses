package com.prilepskiy.thousandsofcourses.domain.di

import com.prilepskiy.thousandsofcourses.data.repository.CourseRepository
import com.prilepskiy.thousandsofcourses.domain.usecase.GetCoursesSortedByPublishDateAscUseCase
import com.prilepskiy.thousandsofcourses.domain.usecase.GetCoursesSortedByPublishDateAscUseCaseImpl
import com.prilepskiy.thousandsofcourses.domain.usecase.GetCoursesSortedByPublishDateDescUseCase
import com.prilepskiy.thousandsofcourses.domain.usecase.GetCoursesSortedByPublishDateDescUseCaseImpl
import com.prilepskiy.thousandsofcourses.domain.usecase.GetCoursesUseCase
import com.prilepskiy.thousandsofcourses.domain.usecase.GetCoursesUseCaseImpl
import com.prilepskiy.thousandsofcourses.domain.usecase.GetLikeListOfUserCoursesUseCase
import com.prilepskiy.thousandsofcourses.domain.usecase.GetLikeListOfUserCoursesUseCaseImpl
import com.prilepskiy.thousandsofcourses.domain.usecase.LikeDislikeUseCase
import com.prilepskiy.thousandsofcourses.domain.usecase.LikeDislikeUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetCoursesSortedByPublishDateAscUseCaseUseCaseModule(repository: CourseRepository): GetCoursesSortedByPublishDateAscUseCase =
        GetCoursesSortedByPublishDateAscUseCaseImpl(repository = repository)

    @Provides
    fun provideGetCoursesSortedByPublishDateDescUseCaseModule(repository: CourseRepository): GetCoursesSortedByPublishDateDescUseCase =
        GetCoursesSortedByPublishDateDescUseCaseImpl(repository = repository)

    @Provides
    fun provideGetCoursesUseCaseModule(repository: CourseRepository): GetCoursesUseCase =
        GetCoursesUseCaseImpl(repository)
    @Provides
    fun providerLikeDesLikeUseCaseModule(repository: CourseRepository): LikeDislikeUseCase=
        LikeDislikeUseCaseImpl(repository)
    @Provides
    fun provideGetCoursesLike(repository: CourseRepository): GetLikeListOfUserCoursesUseCase =
        GetLikeListOfUserCoursesUseCaseImpl(repository)


}