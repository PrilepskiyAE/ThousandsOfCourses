package com.prilepskiy.thousandsofcourses.data.repository

import arrow.core.Either
import com.prilepskiy.thousandsofcourses.data.api.service.CourseService
import com.prilepskiy.thousandsofcourses.data.database.dao.CourseDao
import com.prilepskiy.thousandsofcourses.data.database.entity.CourseEntity
import com.prilepskiy.thousandsofcourses.data.database.entity.toEntity


    interface CourseRepository {
        suspend fun getCourses():Either<String, List<CourseEntity>>
        suspend fun getLikedCourses():Either<String,  List<CourseEntity>>
        suspend fun searchCoursesByTitle(searchTerm: String):Either<String,  List<CourseEntity>>
        suspend fun getCoursesSortedByPublishDateDesc():Either<String,  List<CourseEntity>>
        suspend fun getCoursesSortedByPublishDateAsc():Either<String,  List<CourseEntity>>
        suspend fun update(course:CourseEntity)
    }


class CourseRepositoryImpl(private val dataBase: CourseDao, private val apiService: CourseService): CourseRepository {
    override suspend fun getCourses(): Either<String, List<CourseEntity>> {
        val coursesFromDb = dataBase.getCoursesSortedByPublishDateDesc()
        return if (coursesFromDb.isNotEmpty()) {
            Either.Right(coursesFromDb)
        } else {
            try {
                val apiCourses = apiService.getAllCourse().courses

                if (apiCourses.isEmpty()) {
                    Either.Left("No courses found on the server")
                } else {
                    val coursesWithImages = apiCourses.map {
                        //TODO в ответе нет постеров я добавил свой
                        it.toEntity(
                            "https://drive.google.com/thumbnail?id=1VAxtApfkaEQ6ZVLIeVPgEu3dkaZNKLA5&sz=w500"
                        )
                    }

                    dataBase.insertCourses(coursesWithImages)
                    Either.Right(coursesWithImages)
                }
            } catch (e: Exception) {
                Either.Left("Failed to load courses from API: ${e.message}")
            }
        }
    }


    override suspend fun getLikedCourses():Either<String,  List<CourseEntity>>{
        val course =dataBase.getLikedCourses()
        return if (course.isNotEmpty())
            Either.Right(course)
        else
            Either.Left("No such a courses")
    }

    override suspend fun searchCoursesByTitle(searchTerm: String):Either<String,  List<CourseEntity>> {
        val course =dataBase.searchCoursesByTitle(searchTerm)
        return if (course.isNotEmpty())
            Either.Right(course)
        else
            Either.Left("No such a courses")
    }

    override suspend fun getCoursesSortedByPublishDateDesc():Either<String,  List<CourseEntity>>{
        val course = dataBase.getCoursesSortedByPublishDateDesc()
        return if (course.isNotEmpty())
            Either.Right(course)
        else Either.Left("No such a courses")
    }

    override suspend fun getCoursesSortedByPublishDateAsc():Either<String,  List<CourseEntity>>{
        val course = dataBase.getCoursesSortedByPublishDateAsc()
        return if (course.isNotEmpty())
            Either.Right(course)
        else Either.Left("No such a courses")
    }
    override suspend fun update(course:CourseEntity){
         dataBase.updateCourse(course)
    }

}