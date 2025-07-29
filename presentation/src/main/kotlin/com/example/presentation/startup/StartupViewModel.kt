package com.example.presentation.startup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.userInfo.usecases.GetAuthStateUseCase
import com.example.presentation.core.navigation.AppRouter
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class StartupViewModel(
    private val getAuthStateUseCase: GetAuthStateUseCase,
    private val appRouter: AppRouter,
) : ViewModel() {

    private val _uiEvent = Channel<StartupUiEvent>(
        capacity = Channel.BUFFERED
    )
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getUserAuthState()
    }

    private fun getUserAuthState() {
        viewModelScope.launch {
            getAuthStateUseCase()
                .onSuccess { isAuthenticated ->
                    if (isAuthenticated) {
                        appRouter.navigateToHome()
                    } else {
                        appRouter.navigateToLogin()
                    }
                }.onFailure {
                    _uiEvent.send(StartupUiEvent.ShowErrorToast)
                }
        }
    }
}
