package com.example.data.courses.mapper

import com.example.domain.courses.model.Course
import com.example.network.model.CourseResponse
import java.text.SimpleDateFormat
import java.util.Locale

class CourseMapper {

    fun responseToDomain(from: CourseResponse): Course {
        return Course(
            id = from.id,
            title = from.title,
            description = from.description,
            price = from.price,
            rate = from.rate,
            startDate = formatDate(from.startDate),
            hasLike = from.hasLike,
            publishDate = from.publishDate,
        )
    }

    private fun formatDate(inputDate: String): String {
        val parser = SimpleDateFormat(BASE_DATE_FORMAT, Locale.US)
        val locale = Locale("ru")
        val formatter = SimpleDateFormat(TARGET_DATE_FORMAT, locale)
        return parser.parse(inputDate)?.let { date ->
            formatter.format(date)
                .replaceFirstChar { it.titlecase(locale) }
        }.orEmpty()
    }

    private companion object {
        const val BASE_DATE_FORMAT = "yyyy-MM-dd"
        const val TARGET_DATE_FORMAT = "d MMMM yyyy"
    }
}