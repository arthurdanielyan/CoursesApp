package com.example.domain.userInfo

import com.example.domain.userInfo.model.UserInfo

interface UserRepository {

    suspend fun getUserInfo(): Result<UserInfo?>

    suspend fun login(userInfo: UserInfo): Result<Unit>

    suspend fun logout()
}