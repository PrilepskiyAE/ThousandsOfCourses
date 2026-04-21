package com.prilepskiy.thousandsofcourses.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prilepskiy.thousandsofcourses.data.database.dao.CourseDao
import com.prilepskiy.thousandsofcourses.data.database.entity.CourseEntity


@Database(
    entities = [CourseEntity::class],
    version = VERSION_DATABASE,
    exportSchema = true
)

abstract class CourseDataBase : RoomDatabase() {
    abstract val courseDao: CourseDao
}
const val VERSION_DATABASE = 1

const val DATABASE_NAME = "CourseDataBase"