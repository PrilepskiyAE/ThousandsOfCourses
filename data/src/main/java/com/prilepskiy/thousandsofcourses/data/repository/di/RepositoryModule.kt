package com.prilepskiy.thousandsofcourses.data.repository.di

import com.prilepskiy.thousandsofcourses.data.api.service.CourseService
import com.prilepskiy.thousandsofcourses.data.database.dao.CourseDao
import com.prilepskiy.thousandsofcourses.data.repository.CourseRepository
import com.prilepskiy.thousandsofcourses.data.repository.CourseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class  RepositoryModule {
    @Provides
    fun provideRepositoryModule(dataBase: CourseDao,  apiService: CourseService): CourseRepository = CourseRepositoryImpl(dataBase,apiService)
}
