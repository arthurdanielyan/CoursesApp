package com.example.presentation.main.home.di

import com.example.presentation.main.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {

    viewModelOf(::HomeViewModel)
}