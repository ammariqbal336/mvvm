package com.ixs.testing.mvvm_learning.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.ixs.testing.mvvm_learning.utils.NoNetworkError
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(
    context: Context
) : Interceptor {

    val appcontext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isNetworkAvailable())
                throw NoNetworkError("Check your internet connection")

        return chain.proceed(chain.request())
    }

    private fun isNetworkAvailable():Boolean{
        val connectivityinfo =
            appcontext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityinfo.activeNetworkInfo.also {
            return it !=null && it.isConnected
        }

    }
}