package com.example.coursesapp

import com.example.data.dataModule
import com.example.database.databaseModule
import com.example.domain.domainModule
import com.example.network.networkModule
import com.example.presentation.core.navigation.AppRouter
import com.example.presentation.login.di.loginModule
import com.example.presentation.main.home.di.homeModule
import com.example.presentation.startup.di.startupModule
import com.example.utils.di.utilsModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    singleOf(::AppRouterImpl) bind AppRouter::class

} + databaseModule + dataModule + domainModule + startupModule +
        loginModule + networkModule + homeModule + utilsModule