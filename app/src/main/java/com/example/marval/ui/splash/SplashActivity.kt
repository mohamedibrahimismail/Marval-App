package com.example.marval.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marval.ui.base.BaseActivity
import com.example.marval.R
import com.example.marval.ui.main.MainActivity
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    override fun getActivityView(): Int = R.layout.activity_splash

    override fun afterInflation(savedInstance: Bundle?) {
        Glide.with(this).load(R.drawable.mcu_background)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(backgroundView)

        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            this@SplashActivity.finish()
        }, 3000)
    }

}