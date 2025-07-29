package com.example.presentation.startup.di

import com.example.presentation.startup.StartupViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val startupModule = module {
    viewModelOf(::StartupViewModel)
}