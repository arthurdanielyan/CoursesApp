package com.example.data.courses

import com.example.data.courses.mapper.CourseMapper
import com.example.domain.courses.CoursesRepository
import com.example.domain.courses.model.Course
import com.example.network.CoursesApi

class CoursesRepositoryImpl(
    private val coursesApi: CoursesApi,
    private val courseMapper: CourseMapper,
) : CoursesRepository {

    override suspend fun getCourses(): Result<List<Course>> {
        return runCatching {
            coursesApi.getCourses().courses
                .map(courseMapper::responseToDomain)
        }
    }
}