package com.example.database

import com.example.database.user_info_db.UserInfoDataStore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {

    singleOf(::UserInfoDataStore)
}