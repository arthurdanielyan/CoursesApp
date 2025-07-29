package com.example.domain.courses.usecases

import com.example.domain.courses.CoursesRepository
import com.example.domain.courses.model.Course

class GetCoursesUseCase(
    private val coursesRepository: CoursesRepository,
) {

    suspend operator fun invoke(): Result<List<Course>> {
        return coursesRepository.getCourses()
    }
}