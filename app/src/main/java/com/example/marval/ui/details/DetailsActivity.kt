package com.example.marval.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.marval.R
import com.example.marval.model.main.Result
import com.example.marval.ui.base.BaseActivity
import com.example.marval.utils.bindImage
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

        collabs_back_arrow.setOnClickListener(View.OnClickListener {
            finish()
        })
        toolbar_back_arrow.setOnClickListener(View.OnClickListener {
            finish()
        })

        name_txt.text = details.name
        description_txt.text = details.description
        main_image.bindImage(details.thumbnail.path+"."+ details.thumbnail.extension)

        var comic_adapter = ComicsAdapter(details.comics.items)
        comics_recycler_view.adapter = comic_adapter

        var event_adapter = ComicsAdapter(details.events.items)
        events_recycler_view.adapter = event_adapter

        var series_adapter = ComicsAdapter(details.series.items)
        series_recycler_view.adapter = series_adapter

        var stories_adapter = ComicsAdapter(details.stories.items)
        stories_recycler_view.adapter = stories_adapter
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