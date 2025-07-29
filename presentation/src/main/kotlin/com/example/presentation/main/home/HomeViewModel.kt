package com.example.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.courses.model.Course
import com.example.domain.courses.usecases.GetCoursesUseCase
import com.example.domain.courses.usecases.SortCoursesUseCase
import com.example.presentation.core.viewState.DataLoadingState
import com.example.presentation.core.viewState.toDataLoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val sortCoursesUseCase: SortCoursesUseCase,
) : ViewModel() {

    private val _courses = MutableStateFlow(emptyList<Course>())
    val courses = _courses.asStateFlow()

    private val _loadingState = MutableStateFlow(DataLoadingState.LOADING)
    val loadingState = _loadingState.asStateFlow()

    fun getCourses() {
        viewModelScope.launch {
            _loadingState.update { DataLoadingState.LOADING }
            getCoursesUseCase().let { result ->
                _courses.update { result.getOrDefault(emptyList()) }
                _loadingState.update { result.toDataLoadingState() }
            }
        }
    }

    fun sortCourses() {
        viewModelScope.launch {
            _courses.update { sortCoursesUseCase(it) }
        }
    }
}