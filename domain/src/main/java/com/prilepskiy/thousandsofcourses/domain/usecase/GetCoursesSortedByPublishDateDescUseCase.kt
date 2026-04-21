package com.prilepskiy.thousandsofcourses.domain.usecase

import arrow.core.Either
import com.prilepskiy.thousandsofcourses.data.repository.CourseRepository
import com.prilepskiy.thousandsofcourses.domain.model.CourseModel
import com.prilepskiy.thousandsofcourses.domain.model.toModel

interface GetCoursesSortedByPublishDateDescUseCase {
    suspend operator fun invoke():Either<String, List<CourseModel>>
}
class GetCoursesSortedByPublishDateDescUseCaseImpl(private val repository: CourseRepository) : GetCoursesSortedByPublishDateDescUseCase {
    override suspend fun invoke(): Either<String, List<CourseModel>> = when(val res=repository.getCoursesSortedByPublishDateDesc()){
        is Either.Left ->res
        is Either.Right -> Either.Right(res.value.map { entities -> entities.toModel() })
    }
}