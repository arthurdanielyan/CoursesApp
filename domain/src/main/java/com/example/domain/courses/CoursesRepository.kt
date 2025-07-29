package com.example.domain.courses

import com.example.domain.courses.model.Course

interface CoursesRepository {

    suspend fun getCourses(): Result<List<Course>>
}