package com.prilepskiy.thousandsofcourses.data

import arrow.core.Either
import com.prilepskiy.thousandsofcourses.data.api.service.CourseService
import com.prilepskiy.thousandsofcourses.data.database.dao.CourseDao
import com.prilepskiy.thousandsofcourses.data.database.entity.CourseEntity
import com.prilepskiy.thousandsofcourses.data.repository.CourseRepositoryImpl
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class CourseRepositoryImplGetCoursesTest {

    private lateinit var repository: CourseRepositoryImpl
    private lateinit var mockDao: CourseDao
    private lateinit var mockApi: CourseService


    @Before
    fun setUp() {
        mockDao = mockk()
        mockApi = mockk()
        repository = CourseRepositoryImpl(mockDao, mockApi)
    }

    @Test
    fun `getCourses should return data from database when available`() = runTest {

        val mockCourses = listOf(
            CourseEntity(id = 1, title = "Course 1","","","",0,false, publishDate = 0L,""),
            CourseEntity(id = 2, title = "Course 2","","","",0,false, publishDate = 0L,"")
        )
        coEvery { mockDao.getCoursesSortedByPublishDateDesc() } returns mockCourses
        val result = repository.getCourses()
        assertTrue(result is Either.Right)
        assertEquals(mockCourses, (result as Either.Right).value)

        coVerify(exactly = 1) { mockDao.getCoursesSortedByPublishDateDesc() }
        coVerify(exactly = 0) { mockApi.getAllCourse() }
    }


    @Test
    fun `getLikedCourses should return liked courses when they exist`() = runTest {
        val likedCourses = listOf(
            CourseEntity(id = 1, title = "Course 1","","","",0,true, publishDate = 0L,""),
            CourseEntity(id = 2, title = "Course 2","","","",0,true, publishDate = 0L,"")
        )
        coEvery { mockDao.getLikedCourses() } returns likedCourses
        val result = repository.getLikedCourses()
        assertTrue(result is Either.Right)
        assertEquals(likedCourses, (result as Either.Right).value)
        coVerify(exactly = 1) { mockDao.getLikedCourses() }
    }

}
