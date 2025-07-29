package com.example.utils

import java.io.IOException

class NoInternetException(cause: Throwable? = null) :
    IOException("No internet connection", cause)
