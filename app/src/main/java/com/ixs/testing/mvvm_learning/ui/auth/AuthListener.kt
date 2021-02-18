package com.ixs.testing.mvvm_learning.ui.auth

import androidx.lifecycle.LiveData
import com.ixs.testing.mvvm_learning.data.db.entities.User

interface AuthListener {

    fun onStarted()
    fun onSuccess(user: User)
    fun onFailed(error :  String)
}