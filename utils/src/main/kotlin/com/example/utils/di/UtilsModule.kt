package com.example.utils.di

import com.example.utils.AppDispatchers
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val utilsModule = module {

    single {
        AppDispatchers(
            main = Dispatchers.Main,
            io = Dispatchers.IO,
            default = Dispatchers.Default
        )
    }
}