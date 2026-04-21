package com.prilepskiy.thousandsofcourses.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.prilepskiy.thousandsofcourses.data.database.entity.CourseEntity

@Dao
interface CourseDao {
    @Query("DELETE FROM CourseEntity")
    suspend fun deleteAllCourses()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<CourseEntity>)

    @Update
    suspend fun updateCourse(course: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: CourseEntity): Long

    @Query("SELECT * FROM CourseEntity ORDER BY publishDate DESC")
    suspend fun getCoursesSortedByPublishDateDesc(): List<CourseEntity>

    @Query("SELECT * FROM CourseEntity ORDER BY publishDate ASC")
    suspend fun getCoursesSortedByPublishDateAsc(): List<CourseEntity>

    @Query("SELECT * FROM CourseEntity WHERE hasLike = 1 ORDER BY title ASC")
    suspend fun getLikedCourses(): List<CourseEntity>

    @Query("SELECT * FROM CourseEntity WHERE title LIKE '%' || :searchTerm || '%' ORDER BY title ASC")
    suspend fun searchCoursesByTitle(searchTerm: String): List<CourseEntity>
}