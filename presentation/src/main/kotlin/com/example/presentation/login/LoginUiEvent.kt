package com.example.presentation.login

sealed interface LoginUiEvent {

    data object ShowErrorToast : LoginUiEvent
}