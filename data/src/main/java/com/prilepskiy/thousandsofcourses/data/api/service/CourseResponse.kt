package com.prilepskiy.thousandsofcourses.data.api.service

data class CourseResponse (
    val id: Long,
    val title:String,
    val text:String,
    val price:String,
    val rate:String,
    val startDate:String,
    val hasLike:Boolean,
    val publishDate:String,
)

