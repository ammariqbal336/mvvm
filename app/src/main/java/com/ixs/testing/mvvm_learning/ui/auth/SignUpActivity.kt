package com.ixs.testing.mvvm_learning.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.lifecycleScope
import com.ixs.testing.mvvm_learning.R

import com.ixs.testing.mvvm_learning.databinding.ActivitySignUpBinding

import com.ixs.testing.mvvm_learning.ui.home.HomeActivity
import com.ixs.testing.mvvm_learning.utils.*

import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(),KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()
    private  lateinit var binding : ActivitySignUpBinding
    private  lateinit var viewModel : AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
         viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)

        viewModel.getUser().observe(this, Observer {user ->
            if(user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

        binding.buttonSignUp.setOnClickListener {
            SignUP()
        }

        binding.textViewLogin.setOnClickListener {
            openLogin()
        }
    }

    private fun SignUP(){
        var name = binding.editTextName.text.toString().trim()
        var email = binding.editTextEmail.text.toString().trim()
        var password = binding.editTextPassword.text.toString().trim()
        var confirmpassword = binding.editTextPasswordConfirm.text.toString().trim()

        if(name.isNullOrEmpty()){
              binding.rootLayout.snackbar("Required Name")
              return
        }
        if(email.isNullOrEmpty()){
            binding.rootLayout.snackbar("Required Email")
            return
        }
        if(password.isNullOrEmpty()){
            binding.rootLayout.snackbar("Required Password")
            return

        }
        if(confirmpassword.isNullOrEmpty()){
            binding.rootLayout.snackbar("Required Name")
            return
        }
        if(password != confirmpassword){
            binding.rootLayout.snackbar("Password and Confirm password must be same")
            return
        }

        lifecycleScope.launch {
            try{
                binding.progressBar.show()
                var response  = viewModel.userSignUp(name!!,email!!,password!!)
                binding.progressBar.hide()
                if(response?.user !=null){
                    viewModel.saveLoggedInUser(response.user!!)
                }
                else{
                    binding.rootLayout.snackbar(response.message!!)
                }
            }catch (e: ApiExceptions){
                binding.progressBar.hide()
               Log.e("Error",e.message.toString())
            }catch (e : NoNetworkError){
                binding.progressBar.hide()
                Log.e("Error",e.message.toString())
            }
        }
    }

    private fun openLogin() {
        Intent(this,LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }
}