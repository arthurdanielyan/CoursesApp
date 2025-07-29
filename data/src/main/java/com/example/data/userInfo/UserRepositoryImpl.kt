package com.example.data.userInfo

import com.example.data.userInfo.mapper.UserInfoMapper
import com.example.database.user_info_db.UserInfoDataStore
import com.example.domain.userInfo.model.UserInfo
import com.example.domain.userInfo.UserRepository
import kotlinx.coroutines.flow.firstOrNull

internal class UserRepositoryImpl(
    private val userInfoDataStore: UserInfoDataStore,
    private val userInfoMapper: UserInfoMapper,
) : UserRepository {

    override suspend fun getUserInfo(): Result<UserInfo?> {
        return runCatching {
            userInfoDataStore.userInfo.firstOrNull()?.let(userInfoMapper::entityToDomain)
        }
    }

    override suspend fun login(userInfo: UserInfo): Result<Unit> {
        return runCatching {
            userInfoDataStore.saveUserInfo(userInfoMapper.domainToEntity(userInfo))
        }
    }

    override suspend fun logout() {
        userInfoDataStore.clearUserInfo()
    }
}