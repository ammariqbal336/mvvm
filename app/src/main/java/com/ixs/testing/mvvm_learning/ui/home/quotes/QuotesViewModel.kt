package com.ixs.testing.mvvm_learning.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.ixs.testing.mvvm_learning.data.repository.QuotesRepo
import com.ixs.testing.mvvm_learning.utils.lazyDeferred

class QuotesViewModel(
    quotesRepo: QuotesRepo
) : ViewModel() {
   val quotes by lazyDeferred {
       quotesRepo.getQuotes()
   }

}