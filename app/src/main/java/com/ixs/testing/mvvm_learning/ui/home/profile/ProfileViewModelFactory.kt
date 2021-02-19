package com.ixs.testing.mvvm_learning.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ixs.testing.mvvm_learning.data.repository.UserRepo

class ProfileViewModelFactory(
    private val userRepo: UserRepo
):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(userRepo) as T
    }
}