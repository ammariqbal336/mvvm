package com.ixs.testing.mvvm_learning.data.network.responses

import com.ixs.testing.mvvm_learning.data.db.entities.User

data class AuthResponse(
   var isSuccessful : Boolean?,
   var message : String?,
   var user : User?
)