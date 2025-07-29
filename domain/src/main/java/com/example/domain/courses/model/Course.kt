package com.example.domain.courses.model

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String,
)