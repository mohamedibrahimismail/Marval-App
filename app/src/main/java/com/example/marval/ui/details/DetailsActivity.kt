package com.example.marval.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.marval.R
import com.example.marval.model.main.Result
import com.example.marval.ui.base.BaseActivity
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : BaseActivity() {

    override fun getActivityView(): Int {
        return R.layout.activity_details
    }

    override fun afterInflation(savedInstance: Bundle?) {

        appbar_layout.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                //  Collapsed
                collabs_back_arrow.visibility = View.GONE
                toolbar_back_arrow.visibility = View.VISIBLE
            } else {
                //Expanded
                collabs_back_arrow.visibility = View.VISIBLE
                toolbar_back_arrow.visibility = View.GONE
            }
        })
    }

    companion object {
        lateinit var details: Result
        fun getIntent(
            context: Context,
            details: Result
        ): Intent {
            this.details = details
            return Intent(context, DetailsActivity::class.java)
        }
    }



}