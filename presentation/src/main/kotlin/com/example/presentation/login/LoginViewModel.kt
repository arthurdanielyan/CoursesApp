package com.example.presentation.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.userInfo.model.UserInfo
import com.example.domain.userInfo.usecases.LoginUseCase
import com.example.presentation.core.navigation.AppRouter
import com.example.presentation.login.socialLauncher.SocialLauncher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val socialLauncher: SocialLauncher,
    private val loginUseCase: LoginUseCase,
    private val appRouter: AppRouter,
) : ViewModel() {

    private val _uiEvent = Channel<LoginUiEvent>(
        capacity = Channel.BUFFERED
    )
    val uiEvent = _uiEvent.receiveAsFlow()

    private val password = MutableStateFlow("")
    private val email = MutableStateFlow("")

    private val _isLoginButtonEnabled = MutableStateFlow(false)
    val isLoginButtonEnabled = _isLoginButtonEnabled.asStateFlow()

    fun onEmailType(value: String) {
        email.update { value }
        validateInputs()
    }

    fun onPasswordType(value: String) {
        password.update { value }
        validateInputs()
    }

    fun onVkClick() {
        socialLauncher.openVk()
    }

    fun onOkClick() {
        socialLauncher.openOk()
    }

    fun onLoginClick() {
        viewModelScope.launch {
            loginUseCase(
                userInfo = UserInfo(email = email.value)
            ).onSuccess {
                appRouter.navigateToHome()
            }.onFailure {
                _uiEvent.send(LoginUiEvent.ShowErrorToast)
            }
        }
    }

    private fun validateInputs() {
        _isLoginButtonEnabled.update {
            password.value.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
        }
    }
}