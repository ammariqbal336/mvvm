package com.ixs.testing.mvvm_learning.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ixs.testing.mvvm_learning.data.db.AppDatabase
import com.ixs.testing.mvvm_learning.data.db.entities.User
import com.ixs.testing.mvvm_learning.data.network.MyApi
import com.ixs.testing.mvvm_learning.data.network.SafeAPIRequest
import com.ixs.testing.mvvm_learning.data.network.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepo(
    private val api :MyApi,
    private  val db: AppDatabase
) : SafeAPIRequest(){
    suspend fun LoginUser(email:String,password:String):AuthResponse{
        return  apiRequest {    api.userLogin(email,password)  }
    }

    suspend fun UserSignUp(name : String,email :String,password: String):AuthResponse{
        return apiRequest { api.userSignUp(name,email,password) }
    }

    suspend fun SaveUser(user : User) = db.getUserDao().upsertData(user)

    fun getLoginUser() = db.getUserDao().getUser()

    //        val loginResponse = MutableLiveData<String>()
//
//        MyApi().userLogin(email,password).enqueue(object :Callback<ResponseBody>{
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                loginResponse.value = t.message
//            }
//
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//               if(response.isSuccessful){
//                   loginResponse.value = response.body()?.string()
//               }else{
//                   loginResponse.value = response.errorBody()?.string()
//               }
//
//            }
//
//        })
//        return loginResponse
}