package com.ixs.testing.mvvm_learning.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.ixs.testing.mvvm_learning.data.db.entities.User
import com.ixs.testing.mvvm_learning.data.network.responses.AuthResponse
import com.ixs.testing.mvvm_learning.data.repository.UserRepo
import com.ixs.testing.mvvm_learning.utils.ApiExceptions

import com.ixs.testing.mvvm_learning.utils.Coroutine
import com.ixs.testing.mvvm_learning.utils.NoNetworkError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Response

class AuthViewModel(
    private var userRepo: UserRepo
) : ViewModel(){


    fun getUser()  = userRepo.getLoginUser()

    suspend fun userLogin(
        email : String,
        password : String
    ) = withContext(Dispatchers.IO){ userRepo?.LoginUser(email,password)}

    suspend fun userSignUp(
        name : String,
        email : String,
        password: String
    ) = withContext(Dispatchers.IO){ userRepo?.UserSignUp(name,email,password)}

    suspend fun saveLoggedInUser(user : User) = userRepo.SaveUser(user)

   /* fun onButtonClick(view : View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailed("Invalid email or password")
            return
        }


        Coroutine.main {
            try {
                var response =  userRepo.LoginUser(email!!,password!!)
                response.user?.let {
                    authListener?.onSuccess(it)
                    userRepo.SaveUser(it)
                    return@main
                }
               authListener?.onFailed(response.message!!)

            }catch (e:ApiExceptions){
                authListener?.onFailed(e.message!!)
            }catch (e : NoNetworkError){
                authListener?.onFailed(e.message!!)
            }


        }

        //authListener?.onSuccess(response)
        //authListener?.onSuccess()
    }

    fun LoginScreen(view: View){
        Intent(view.context,LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            view.context.startActivity(it)
        }
    }
    fun SignUpScreen(view: View){
        Intent(view.context,SignUpActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            view.context.startActivity(it)
        }
    }

    fun onSignUp(view :View){
        authListener?.onStarted()
        if(name.isNullOrEmpty()){
            authListener?.onFailed("Required Name")
            return
        }
        if(email.isNullOrEmpty()){
            authListener?.onFailed("Required Email")
            return
        }
        if(password.isNullOrEmpty()){
            authListener?.onFailed("Required Password")
            return
        }
        if(confirmpassword.isNullOrEmpty()){
            authListener?.onFailed("Required Confirm Password")
            return
        }
        if(password != confirmpassword){
            authListener?.onFailed("Password and Confirm password must be same")
            return
        }

        Coroutine.main {
            try{

            var response  = userRepo.UserSignUp(name!!,email!!,password!!)
            response.user?.let {
                authListener?.onSuccess(it)
                userRepo.SaveUser(it)
                return@main
            }
                authListener?.onFailed(response.message!!)

            }catch (e:ApiExceptions){
                authListener?.onFailed(e.message!!)
            }catch (e : NoNetworkError){
                authListener?.onFailed(e.message!!)
            }

        }


    }*/
}