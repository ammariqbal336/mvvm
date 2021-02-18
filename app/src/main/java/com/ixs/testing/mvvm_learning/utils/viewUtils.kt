package com.ixs.testing.mvvm_learning.utils

import android.content.Context
import android.os.Message
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

fun ProgressBar.show(){
    visibility= View.VISIBLE
}
fun  ProgressBar.hide(){
    visibility= View.GONE
}

fun View.snackbar(message: String){
    Snackbar.make(this,message,Snackbar.LENGTH_SHORT).also { snackbar ->
        snackbar.setAction("Ok"){
            snackbar.dismiss()
        }
    }.show()
}