package com.example.presentation.core.viewState

import com.example.utils.NoInternetException

enum class DataLoadingState {
    LOADING, SUCCESS, ERROR, NETWORK_ERROR;
}

fun <T> Result<T>.toDataLoadingState(): DataLoadingState {
    return when {
        this.isFailure -> {
            when (this.exceptionOrNull()) {
                is NoInternetException -> DataLoadingState.NETWORK_ERROR
                else -> DataLoadingState.ERROR
            }
        }
        else -> DataLoadingState.SUCCESS
    }
}
