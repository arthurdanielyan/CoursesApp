package com.example.domain.userInfo.usecases

import com.example.domain.userInfo.UserRepository

class GetAuthStateUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): Result<Boolean> {
        return userRepository.getUserInfo().map {
            it != null
        }
    }
}