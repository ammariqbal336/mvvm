package com.ixs.testing.mvvm_learning.data.network

import com.ixs.testing.mvvm_learning.utils.ApiExceptions

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.StringBuilder

abstract class SafeAPIRequest{
   suspend fun<T: Any> apiRequest(call : suspend () -> Response<T>):T{
        val response = call.invoke()
       if(response.isSuccessful){
           return response.body()!!
       }
       else{
           var message = StringBuilder()
           val error  = response.errorBody()?.string()

               error?.let {
                   try {
                       message.append(JSONObject(it).get("message"))
                   }catch (e: JSONException){}
                   message.append("\n")
               }
           message.append("Error code ${response.code()}")
           throw ApiExceptions(message.toString())
       }
    }
}