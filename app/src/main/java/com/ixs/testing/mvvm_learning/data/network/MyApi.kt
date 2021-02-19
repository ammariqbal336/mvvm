package com.ixs.testing.mvvm_learning.data.network

import com.ixs.testing.mvvm_learning.data.network.responses.AuthResponse
import com.ixs.testing.mvvm_learning.data.network.responses.QuoteResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi{
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ): Response<AuthResponse>

    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignUp(
        @Field("name") name : String,
        @Field("email") email: String,
        @Field("password") password: String
    ):Response<AuthResponse>

    @GET("quotes")
    suspend fun getQuotes() : Response<QuoteResponse>

    companion object{
        operator fun invoke(
            networkInterceptor: NetworkInterceptor
        ) :MyApi{
            val okhttpclient = OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okhttpclient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}