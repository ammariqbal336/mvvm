package com.ixs.testing.mvvm_learning.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ixs.testing.mvvm_learning.R
import com.ixs.testing.mvvm_learning.data.db.entities.User
import com.ixs.testing.mvvm_learning.databinding.LoginactivityBinding
import com.ixs.testing.mvvm_learning.ui.home.HomeActivity
import com.ixs.testing.mvvm_learning.utils.*
import kotlinx.android.synthetic.main.loginactivity.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(),KodeinAware {

    override val kodein by kodein()

    private val factory : AuthViewModelFactory by instance()
    private  lateinit var binding : LoginactivityBinding
    private  lateinit var viewModel : AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


      binding  = DataBindingUtil.setContentView(this,R.layout.loginactivity)
      viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)

        viewModel.getUser().observe(this, Observer {user ->
            if(user != null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

        binding.buttonSignIn.setOnClickListener {
            loginUser()
        }
        binding.textViewSignUp.setOnClickListener{
            openSignUp()
        }
    }

    private fun openSignUp() {
        Intent(this,SignUpActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    fun loginUser(){
        var email = binding.editTextEmail.text.toString().trim()
        var password = binding.editTextPassword.text.toString().trim()

        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            binding.rootLayout.snackbar("Invalid username or password")
        }
        else {
            try {
                binding.progressBar.show()
                lifecycleScope.launch {
                    var response = viewModel.userLogin(email, password)
                    binding.progressBar.hide()
                    if (response.user != null) {
                        viewModel.saveLoggedInUser(response.user!!)
                    } else {
                        binding.rootLayout.snackbar(response.message!!)
                    }
                }
            } catch (e: ApiExceptions) {
                binding.progressBar.hide()
                Log.e("Error",e.printStackTrace().toString())
            } catch (e: NoNetworkError) {
                binding.progressBar.hide()
                Log.e("Error",e.printStackTrace().toString())
            }
        }

    }
}