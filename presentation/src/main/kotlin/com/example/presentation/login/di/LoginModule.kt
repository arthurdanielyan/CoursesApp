package com.example.presentation.login.di

import com.example.presentation.login.LoginViewModel
import com.example.presentation.login.socialLauncher.SocialLauncher
import com.example.presentation.login.socialLauncher.SocialLauncherImpl
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    viewModelOf(::LoginViewModel)

    factory<SocialLauncher> {
        SocialLauncherImpl(
            context = get()
        )
    }
}