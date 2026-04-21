package com.prilepskiy.thousandsofcourses.domain.usecase

import com.prilepskiy.thousandsofcourses.data.repository.CourseRepository
import com.prilepskiy.thousandsofcourses.domain.model.CourseModel
import com.prilepskiy.thousandsofcourses.domain.model.toEntity

interface LikeDislikeUseCase {
    suspend operator fun invoke(courseModel: CourseModel)
}

class LikeDislikeUseCaseImpl(private val repository: CourseRepository) : LikeDislikeUseCase {
    override suspend fun invoke(courseModel: CourseModel) {
        val res =courseModel.toEntity().copy(hasLike = !courseModel.hasLike)
        repository.update(res)
    }
}