package com.ixs.testing.mvvm_learning.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ixs.testing.mvvm_learning.data.db.entities.Quotes

@Dao
interface QuoteDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertQuotes(quotes : List<Quotes>)

    @Query("Select * from Quotes")
    fun getQuotes(): LiveData<List<Quotes>>
}