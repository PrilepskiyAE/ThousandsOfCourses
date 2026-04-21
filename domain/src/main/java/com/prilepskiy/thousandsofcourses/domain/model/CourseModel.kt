package com.prilepskiy.thousandsofcourses.domain.model

import com.prilepskiy.thousandsofcourses.common.DATE_FORMAT_PATTERN_MODEL
import com.prilepskiy.thousandsofcourses.common.dateStringToTimestamp
import com.prilepskiy.thousandsofcourses.common.timestampToDateString
import com.prilepskiy.thousandsofcourses.data.database.entity.CourseEntity

data class CourseModel(
    val id: Long,
    val title:String,
    val text:String,
    val price:String,
    val rate:String,
    val startDate:String,
    val hasLike:Boolean,
    val publishDate:String,
    val posterUrl : String,
)



fun CourseEntity.toModel(): CourseModel {
    return CourseModel(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = timestampToDateString(startDate,DATE_FORMAT_PATTERN_MODEL),
        hasLike = hasLike,
        publishDate =timestampToDateString(publishDate,DATE_FORMAT_PATTERN_MODEL),
        posterUrl=posterUrl
    )
}



fun CourseModel.toEntity(): CourseEntity {
    return CourseEntity(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = dateStringToTimestamp(startDate,DATE_FORMAT_PATTERN_MODEL),
        hasLike = hasLike,
        publishDate = dateStringToTimestamp(publishDate,DATE_FORMAT_PATTERN_MODEL),
        posterUrl=posterUrl
    )
}
