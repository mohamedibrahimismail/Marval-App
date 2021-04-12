package com.example.marval.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.marval.ui.base.BaseActivity
import com.example.marval.R
import com.example.marval.ui.main.MainActivity

class SplashActivity : BaseActivity() {
    override fun getActivityView(): Int = R.layout.activity_splash

    override fun afterInflation(savedInstance: Bundle?) {
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            this@SplashActivity.finish()
        }, 2000)
    }

}