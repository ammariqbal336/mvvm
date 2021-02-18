package com.ixs.testing.mvvm_learning.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USERID = 0
@Entity
data class User(
    var id: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var email_verified_at: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null
)  {
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USERID
}