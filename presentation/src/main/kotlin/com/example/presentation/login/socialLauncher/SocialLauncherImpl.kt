package com.example.presentation.login.socialLauncher

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import androidx.core.net.toUri

class SocialLauncherImpl(
    private val context: Context
): SocialLauncher {

    private companion object {
        const val VK_LINK = "https://vk.com/"
        const val OK_LINK = "https://ok.ru/"
    }

    override fun openVk() {
        val intent = Intent(Intent.ACTION_VIEW, VK_LINK.toUri()).apply {
            flags = FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }

    override fun openOk() {
        val intent = Intent(Intent.ACTION_VIEW, OK_LINK.toUri()).apply {
            flags = FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}