package com.ixs.testing.mvvm_learning.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

object Coroutine{
    fun main(work : suspend(()-> Unit)){
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
    }

}