package com.example.data.userInfo.mapper

import com.example.database.user_info_db.UserInfoEntity
import com.example.domain.userInfo.model.UserInfo

internal class UserInfoMapper {

    fun entityToDomain(from: UserInfoEntity): UserInfo {
        return UserInfo(
            email = from.email,
        )
    }

    fun domainToEntity(from: UserInfo): UserInfoEntity {
        return UserInfoEntity(
            email = from.email,
        )
    }
}