package com.example.marval.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.marval.R
import com.example.marval.model.main.Result
import com.example.marval.ui.base.BaseActivity
import com.example.marval.utils.bindImage
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsActivity : BaseActivity() {
    private val detailVM: DetailsVM by viewModel()
    override fun getActivityView(): Int {
        return R.layout.activity_details
    }

    override fun afterInflation(savedInstance: Bundle?) {
        setupObserver()
        detailVM.getComicsList(details.id.toString())
        detailVM.getEventsList(details.id.toString())
        detailVM.getSeriesList(details.id.toString())
        detailVM.getStoriesList(details.id.toString())
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
        main_image.bindImage(details.thumbnail.path + "." + details.thumbnail.extension)

    }

    fun setupObserver() {
        detailVM.loading.observe(this, Observer {
            showLoading(it)
        })
        detailVM.comicsList.observe(this, Observer {
            if (!it.results.isNullOrEmpty()) {
                var comic_adapter = ComicsAdapter(it.results)
                comics_recycler_view.adapter = comic_adapter
            }
        })
        detailVM.eventsList.observe(this, Observer {
            if (!it.results.isNullOrEmpty()) {
                var adapter = ComicsAdapter(it.results)
                events_recycler_view.adapter = adapter
            }
        })
        detailVM.seriesList.observe(this, Observer {
            if (!it.results.isNullOrEmpty()) {
                var adapter = ComicsAdapter(it.results)
                series_recycler_view.adapter = adapter
            }
        })

        detailVM.storiesList.observe(this, Observer {
            if (!it.results.isNullOrEmpty()) {
                var adapter = ComicsAdapter(it.results)
                stories_recycler_view.adapter = adapter
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