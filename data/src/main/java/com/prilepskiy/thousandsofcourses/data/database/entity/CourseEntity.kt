package com.prilepskiy.thousandsofcourses.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prilepskiy.thousandsofcourses.common.dateStringToTimestamp
import com.prilepskiy.thousandsofcourses.data.api.service.CourseResponse

@Entity
data class CourseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title:String,
    val text:String,
    val price:String,
    val rate:String,
    val startDate:Long,
    val hasLike:Boolean,
    val publishDate:Long,
    val posterUrl : String
)
fun CourseResponse.toEntity(posterUrl: String): CourseEntity {
    return CourseEntity(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = dateStringToTimestamp(startDate ),
        hasLike = hasLike,
        publishDate = dateStringToTimestamp(publishDate),
        posterUrl = posterUrl
    )
}

