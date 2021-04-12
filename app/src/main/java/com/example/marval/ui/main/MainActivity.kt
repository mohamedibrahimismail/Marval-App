package com.example.marval.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import com.example.marval.ui.base.BaseActivity
import com.example.marval.R

class MainActivity : BaseActivity() {
    lateinit var navController: NavController

    override fun getActivityView(): Int = R.layout.activity_main

    override fun afterInflation(savedInstance: Bundle?) {
        setupViews()
    }

    fun setupViews() {


    }


}
