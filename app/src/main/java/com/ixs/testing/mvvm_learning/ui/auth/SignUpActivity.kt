package com.ixs.testing.mvvm_learning.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ixs.testing.mvvm_learning.R
import com.ixs.testing.mvvm_learning.data.db.entities.User
import com.ixs.testing.mvvm_learning.databinding.ActivitySignUpBinding
import com.ixs.testing.mvvm_learning.databinding.LoginactivityBinding
import com.ixs.testing.mvvm_learning.ui.home.HomeActivity
import com.ixs.testing.mvvm_learning.utils.hide
import com.ixs.testing.mvvm_learning.utils.show
import com.ixs.testing.mvvm_learning.utils.snackbar
import com.ixs.testing.mvvm_learning.utils.toast
import kotlinx.android.synthetic.main.loginactivity.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(),AuthListener,KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding :  ActivitySignUpBinding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
        viewModel.getUser().observe(this, Observer {user ->
            if(user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progress_bar.show()
        toast("On Started")
    }

    override fun onSuccess(user : User) {
        progress_bar.hide()
    }

    override fun onFailed(error: String) {
        progress_bar.hide()
        root_layout.snackbar(error)
    }
}