package com.ixs.testing.mvvm_learning.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
        var result = false
        val connectivityManager = appcontext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when{
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    else -> false
                }
            }
        }
        return result

    }
}