package com.prilepskiy.thousandsofcourses.data.database.di

import android.content.Context
import androidx.room.Room
import com.prilepskiy.thousandsofcourses.data.database.CourseDataBase
import com.prilepskiy.thousandsofcourses.data.database.DATABASE_NAME
import com.prilepskiy.thousandsofcourses.data.database.dao.CourseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): CourseDataBase =
        Room.databaseBuilder(
            context = context,
            klass = CourseDataBase::class.java,
            name = DATABASE_NAME
        ).build()
}

@Module
@InstallIn(SingletonComponent::class)
object CourseDaoModule {
    @Provides
    fun provideCourseDao(database: CourseDataBase): CourseDao =
        database.courseDao
}