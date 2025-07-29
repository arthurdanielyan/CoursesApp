package com.example.presentation.main.home

import com.example.domain.courses.model.Course
import com.example.presentation.core.viewState.DataLoadingState

data class HomeViewState(
    val courses: List<Course> = emptyList(),
    val loadingState: DataLoadingState = DataLoadingState.LOADING,
)
