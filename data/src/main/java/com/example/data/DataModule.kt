package com.example.data

import com.example.data.courses.CoursesRepositoryImpl
import com.example.data.courses.mapper.CourseMapper
import com.example.data.userInfo.mapper.UserInfoMapper
import com.example.data.userInfo.UserRepositoryImpl
import com.example.domain.courses.CoursesRepository
import com.example.domain.userInfo.UserRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {

    single<UserRepository> {
        UserRepositoryImpl(
            userInfoDataStore = get(),
            userInfoMapper = get(),
        )
    }

    single<CoursesRepository> {
        CoursesRepositoryImpl(
            coursesApi = get(),
            courseMapper = get(),
        )
    }

    factoryOf(::UserInfoMapper)
    factoryOf(::CourseMapper)
}