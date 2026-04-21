package com.prilepskiy.thousandsofcourses.data.api.service

import retrofit2.http.GET

interface CourseService {
    @GET("/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun getAllCourse(): ApiResponse<CourseResponse>
}