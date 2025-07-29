package com.example.domain.userInfo.usecases

import com.example.domain.userInfo.model.UserInfo
import com.example.domain.userInfo.UserRepository

class GetUserInfoUseCase(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(): Result<UserInfo?> {
        return userRepository.getUserInfo()
    }
}