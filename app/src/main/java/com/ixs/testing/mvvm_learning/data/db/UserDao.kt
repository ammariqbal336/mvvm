package com.ixs.testing.mvvm_learning.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ixs.testing.mvvm_learning.data.db.entities.CURRENT_USERID
import com.ixs.testing.mvvm_learning.data.db.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertData(user:User):Long

    @Query("SELECT * FROM user where uid = $CURRENT_USERID")
    fun getUser(): LiveData<User>
}