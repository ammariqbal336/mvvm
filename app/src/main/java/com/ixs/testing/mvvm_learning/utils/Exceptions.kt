package com.ixs.testing.mvvm_learning.utils

import java.io.IOException

class ApiExceptions(message: String) :IOException(message)

class NoNetworkError(message: String) : IOException(message)