package com.example.marval.ui.main

import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.ViewAnimator
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marval.R
import com.example.marval.ui.base.BaseActivity
import com.example.marval.ui.details.DetailsActivity
import com.example.marval.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {
    private val mainVM: MainVM by viewModel()
    private var adapter = MainActivityAdapter();
    override fun getActivityView(): Int = R.layout.activity_main

    override fun afterInflation(savedInstance: Bundle?) {
        setupViews()
        setupObserver()
        mainVM.getCharactersList()
    }

    fun setupObserver() {
        mainVM.loading.observe(this, Observer {
            showLoading(it)
        })
        mainVM.charactersList.observe(this, Observer {
            if (!it.results.isNullOrEmpty())
                adapter.addToList(it.results)
        })
        mainVM.paginationLoaderStatus.observe(this, Observer {
            if (it) {
                pagination_loader.visibility = View.VISIBLE
            } else {
                pagination_loader.visibility = View.GONE
            }
        })
    }

    fun setupViews() {
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        main_recycler_view.layoutManager = linearLayoutManager
        val divider = DividerItemDecoration(
            this,
            DividerItemDecoration.VERTICAL
        )

        divider.setDrawable(ShapeDrawable().apply {
            intrinsicHeight = resources.getDimensionPixelOffset(R.dimen.dp_10)
            paint.color =
                resources.getColor(R.color.body_color) // note: currently (support version 28.0.0), we can not use tranparent color here, if we use transparent, we still see a small divider line. So if we want to display transparent space, we can set color = background color or we can create a custom ItemDecoration instead of DividerItemDecoration.
        })
        main_recycler_view.addItemDecoration(divider)
        main_recycler_view.adapter = adapter
        main_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    mainVM.getCharactersList()
                }
            }
        })
        search.setOnClickListener(View.OnClickListener {
            startActivity(SearchActivity.getIntent(it.context,adapter.list))
        })

    }


}
