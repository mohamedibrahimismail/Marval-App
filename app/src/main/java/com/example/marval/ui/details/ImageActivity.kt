package com.example.marval.ui.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.marval.R
import com.example.marval.model.main.Result
import com.example.marval.utils.bindImage
import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.android.synthetic.main.main_recycler_item.*

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        myZoomageView.bindImage(Image)
        back_arrow.setOnClickListener(View.OnClickListener {
            finish()
        })

    }

    companion object {
        lateinit var Image: String
        fun getIntent(
            context: Context,
            Image: String
        ): Intent {
            this.Image = Image
            return Intent(context, ImageActivity::class.java)
        }
    }

}