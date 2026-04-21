package com.prilepskiy.thousandsofcourses.domain.usecase

import arrow.core.Either
import com.prilepskiy.thousandsofcourses.data.database.entity.CourseEntity
import com.prilepskiy.thousandsofcourses.data.repository.CourseRepository
import com.prilepskiy.thousandsofcourses.domain.model.CourseModel
import com.prilepskiy.thousandsofcourses.domain.model.toModel

interface GetCoursesUseCase {
    suspend operator fun invoke(): Either<String, List<CourseModel>>
}

class GetCoursesUseCaseImpl(private val repository: CourseRepository) : GetCoursesUseCase {
    override suspend fun invoke(): Either<String, List<CourseModel>> =
        when (val res = repository.getCourses()) {
            is Either.Left -> res
            is Either.Right -> Either.Right(res.value.map { entities -> entities.toModel() })
        }
}