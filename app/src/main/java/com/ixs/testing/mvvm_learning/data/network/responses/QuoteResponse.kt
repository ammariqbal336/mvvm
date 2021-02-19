package com.ixs.testing.mvvm_learning.data.network.responses

import com.ixs.testing.mvvm_learning.data.db.entities.Quotes

data class QuoteResponse(

     val isSuccessful : Boolean?,
     val quotes : List<Quotes>
)