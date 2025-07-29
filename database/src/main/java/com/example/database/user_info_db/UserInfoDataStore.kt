package com.example.database.user_info_db

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserInfoDataStore(
    private val applicationContext: Context,
    applicationScope: CoroutineScope,
) {

    private val Context.userInfoStore by preferencesDataStore(
        name = DATA_STORE_NAME,
        scope = applicationScope
    )

    private val dataStore
        get() = applicationContext.userInfoStore

    val userInfo: Flow<UserInfoEntity?> =
        dataStore.data
            .map { prefs ->
                prefs[USER_EMAIL_KEY]?.let { UserInfoEntity(it) }
            }


    suspend fun saveUserInfo(user: UserInfoEntity) {
        dataStore.edit { prefs ->
            prefs[USER_EMAIL_KEY] = user.email
        }
    }

    suspend fun clearUserInfo() {
        dataStore.edit { prefs ->
            prefs.remove(USER_EMAIL_KEY)
        }
    }

    private companion object {
        const val DATA_STORE_NAME = "user_info_prefs"

        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }
}