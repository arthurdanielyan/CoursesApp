package com.example.presentation.startup


sealed interface StartupUiEvent {

    data object ShowErrorToast : StartupUiEvent
}