package com.ixs.testing.mvvm_learning.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.ixs.testing.mvvm_learning.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)
        var navcontroller = Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupWithNavController(nav_view,navcontroller)
        NavigationUI.setupActionBarWithNavController(this,navcontroller,drawer_layout)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this,R.id.fragment),
            drawer_layout
        )
    }
}