package com.example.marval.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marval.R
import com.example.marval.model.main.Result
import com.example.marval.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity() {
    private val searchVM: SearchVM by viewModel()
    private var adapter = SearchAdapter(arrayListOf());

    override fun getActivityView(): Int {
        return R.layout.activity_search
    }

    override fun afterInflation(savedInstance: Bundle?) {
        setupViews()
        setupObserver()
    }

    fun setupViews() {
        cancel_txt.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun setupObserver() {
        searchVM.loading.observe(this, Observer {
            if(it){
                loader.visibility = View.VISIBLE
            }else{
                loader.visibility = View.GONE
            }
        })
        search_box.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (search_box.text.trim().isEmpty()) {
                    search_recyclerview.visibility = View.GONE
                } else {
                    search_recyclerview.visibility = View.VISIBLE
                    //adapter.filter(search_box.text.toString())
                    searchVM.getCharactersList(search_box.text.trim().toString())
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        searchVM.charactersList.observe(this, Observer {
                setupRecyclerView(it.results)
        })

    }

    fun setupRecyclerView(results: List<Result>) {
        adapter = SearchAdapter(results);
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        search_recyclerview.layoutManager = linearLayoutManager
        search_recyclerview.adapter = adapter
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