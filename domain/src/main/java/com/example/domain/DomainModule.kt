package com.example.domain

import com.example.domain.courses.usecases.GetCoursesUseCase
import com.example.domain.courses.usecases.SortCoursesUseCase
import com.example.domain.userInfo.usecases.GetAuthStateUseCase
import com.example.domain.userInfo.usecases.GetUserInfoUseCase
import com.example.domain.userInfo.usecases.LoginUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {

    factoryOf(::GetUserInfoUseCase)
    factoryOf(::GetAuthStateUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::GetCoursesUseCase)
    factoryOf(::SortCoursesUseCase)
}
