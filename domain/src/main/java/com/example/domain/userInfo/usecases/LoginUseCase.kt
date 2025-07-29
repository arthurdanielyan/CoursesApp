package com.example.domain.userInfo.usecases

import com.example.domain.userInfo.model.UserInfo
import com.example.domain.userInfo.UserRepository

class LoginUseCase(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(userInfo: UserInfo): Result<Unit> {
        return userRepository.login(userInfo)
    }
}