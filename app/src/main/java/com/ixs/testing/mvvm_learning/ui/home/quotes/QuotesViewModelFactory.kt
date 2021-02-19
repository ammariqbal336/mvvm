package com.ixs.testing.mvvm_learning.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ixs.testing.mvvm_learning.data.repository.QuotesRepo

class QuotesViewModelFactory(
 private val quotesRepo: QuotesRepo
) :ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(quotesRepo) as T
    }

}