package com.ixs.testing.mvvm_learning.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ixs.testing.mvvm_learning.data.db.AppDatabase
import com.ixs.testing.mvvm_learning.data.db.entities.Quotes
import com.ixs.testing.mvvm_learning.data.network.MyApi
import com.ixs.testing.mvvm_learning.data.network.SafeAPIRequest
import com.ixs.testing.mvvm_learning.data.preference.PreferenceProvider
import com.ixs.testing.mvvm_learning.utils.Coroutine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val  Last_Updated_hour = 6
class QuotesRepo(
    private val myApi: MyApi,
    private val db: AppDatabase,
    private val prefs : PreferenceProvider
) :SafeAPIRequest(){

    private val quotes  = MutableLiveData<List<Quotes>>()

    init {
        quotes.observeForever{
            saveQuotes(it)
        }
    }

    suspend fun getQuotes():LiveData<List<Quotes>>{
       return withContext(Dispatchers.IO){
           fetchQuotes()
           db.getQuotesDao().getQuotes()
       }
    }

    private suspend fun fetchQuotes(){
        val savedat = prefs.getLastSavedAt()

        if(savedat == null  || isFetchNeeded(LocalDateTime.parse(savedat))){
            val response = apiRequest { myApi.getQuotes()}
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt,LocalDateTime.now()) > Last_Updated_hour
    }

    private fun saveQuotes(quotes: List<Quotes>) {
       Coroutine.io {
           prefs.saveLastUpdate(LocalDateTime.now().toString())
           db.getQuotesDao().InsertQuotes(quotes)
       }

    }
}