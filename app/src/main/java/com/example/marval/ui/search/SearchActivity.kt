package com.example.marval.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marval.R
import com.example.marval.model.main.Result
import com.example.marval.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity() {
    private val searchVM: SearchVM by viewModel()
    private var adapter = SearchAdapter(list_results);

    override fun getActivityView(): Int {
        return R.layout.activity_search
    }

    override fun afterInflation(savedInstance: Bundle?) {
        setupViews()
        setupObserver()
//        searchVM.getCharactersList()
    }

    fun setupViews() {
        cancel_txt.setOnClickListener(View.OnClickListener {
            finish()
        })
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        search_recyclerview.layoutManager = linearLayoutManager
        search_recyclerview.adapter = adapter
    }

    fun setupObserver() {
        searchVM.loading.observe(this, Observer {
            showLoading(it)
        })
        search_box.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                adapter.filter(search_box.text.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
//        searchVM.charactersList.observe(this, Observer {
//            if (!it.results.isNullOrEmpty())
//                adapter.addToList(it.results)
//        })

    }

    companion object {
        lateinit var list_results: List<Result>
        fun getIntent(
            context: Context,
            list: List<Result>
        ): Intent {
            this.list_results = list
            return Intent(context, SearchActivity::class.java)
        }
    }


}