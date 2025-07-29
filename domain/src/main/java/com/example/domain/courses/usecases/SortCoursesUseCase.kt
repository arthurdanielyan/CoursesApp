package com.example.domain.courses.usecases

import com.example.domain.courses.model.Course
import com.example.utils.AppDispatchers
import kotlinx.coroutines.withContext

class SortCoursesUseCase(
    private val dispatchers: AppDispatchers,
) {

    suspend operator fun invoke(courses: List<Course>): List<Course> {
        return withContext(dispatchers.main) { // sorting might take long
            courses.sortedBy { it.publishDate }
        }
    }
}