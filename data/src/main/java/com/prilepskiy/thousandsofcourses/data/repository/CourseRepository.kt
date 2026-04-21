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

/**
 * Implementation of CourseRepository that uses a local database (Room) as primary data source
 * and falls back to remote API when necessary. Combines local persistence with network fetching
 * to provide offline-first behavior.
 */
class CourseRepositoryImpl(
    private val dataBase: CourseDao,
    private val apiService: CourseService
) : CourseRepository {

    /**
     * Retrieves all available courses. First attempts to get data from local database.
     * If database is empty, fetches courses from remote API, saves them to database,
     * and returns the fetched data.
     *
     * @return Either containing:
     *         - Left(String) with error message if API call fails
     *         - Right(List<CourseEntity>) with list of courses (from DB or API)
     *
     * @note When fetching from API, assigns a default thumbnail URL to all courses
     *       since the API response doesn't include image information
     */
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
                        // TODO: Replace with actual image URLs when API provides them
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

    /**
     * Retrieves courses marked as liked by the user from the local database.
     *
     * @return Either containing:
     *         - Left(String) if no liked courses are found
     *         - Right(List<CourseEntity>) with list of liked courses
     */
    override suspend fun getLikedCourses(): Either<String, List<CourseEntity>> {
        val course = dataBase.getLikedCourses()
        return if (course.isNotEmpty())
            Either.Right(course)
        else
            Either.Left("No liked courses found")
    }

    /**
     * Searches courses by title in the local database using partial, case‑insensitive matching.
     *
     * @param searchTerm The search query string to look for in course titles
     * @return Either containing:
     *         - Left(String) if no courses match the search term
     *         - Right(List<CourseEntity>) with list of matching courses
     */
    override suspend fun searchCoursesByTitle(searchTerm: String): Either<String, List<CourseEntity>> {
        val course = dataBase.searchCoursesByTitle(searchTerm)
        return if (course.isNotEmpty())
            Either.Right(course)
        else
            Either.Left("No courses found matching '$searchTerm'")
    }

    /**
     * Retrieves all courses from the local database sorted by publication date
     * in descending order (newest first).
     *
     * @return Either containing:
     *         - Left(String) if no courses are available in the database
     *         - Right(List<CourseEntity>) with sorted list of courses
     */
    override suspend fun getCoursesSortedByPublishDateDesc(): Either<String, List<CourseEntity>> {
        val course = dataBase.getCoursesSortedByPublishDateDesc()
        return if (course.isNotEmpty())
            Either.Right(course)
        else Either.Left("No courses available in the database")
    }

    /**
     * Retrieves all courses from the local database sorted by publication date
     * in ascending order (oldest first).
     *
     * @return Either containing:
     *         - Left(String) if no courses are available in the database
     *         - Right(List<CourseEntity>) with sorted list of courses
     */
    override suspend fun getCoursesSortedByPublishDateAsc(): Either<String, List<CourseEntity>> {
        val course = dataBase.getCoursesSortedByPublishDateAsc()
        return if (course.isNotEmpty())
            Either.Right(course)
        else Either.Left("No courses available in the database")
    }

    /**
     * Updates an existing course entity in the local database.
     *
     * @param course The CourseEntity object with updated data to be persisted
     * @throws Exception if update operation fails (handled by calling context)
     * @return Unit — this function doesn't return a value. On failure, the exception
     *         should be handled by the caller.
     *
     * @note For consistency, consider changing return type to Either<String, Unit>
     *       in future versions to align with other repository methods
     */
    override suspend fun update(course: CourseEntity) {
        dataBase.updateCourse(course)
    }
}