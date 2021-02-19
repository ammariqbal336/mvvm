package com.ixs.testing.mvvm_learning.ui.home.profile

import androidx.lifecycle.ViewModel
import com.ixs.testing.mvvm_learning.data.repository.UserRepo

class ProfileViewModel(
    userRepo: UserRepo
) : ViewModel() {

    val user = userRepo.getLoginUser()
}