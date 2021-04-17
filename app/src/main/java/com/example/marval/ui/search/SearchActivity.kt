package com.example.marval.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.lifecycle.Observer
import com.example.marval.R
import com.example.marval.model.main.Result
import com.example.marval.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


class SearchActivity : BaseActivity() {
    private val searchVM: SearchVM by viewModel()
    lateinit var adapter:SearchAdapter
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
            if (it) {
                loader.visibility = View.VISIBLE
            } else {
                loader.visibility = View.GONE
            }
        })




        search_box.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (search_box.text.trim().isEmpty()) {
                    search_recyclerview.visibility = View.GONE
                } else {
                    search_recyclerview.visibility = View.VISIBLE
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
        adapter = SearchAdapter(search_box.text.trim().toString(),results);
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

    @ExperimentalCoroutinesApi
    @CheckResult
    fun EditText.textChanges(): Flow<CharSequence?> {
        return callbackFlow<CharSequence?> {
            val listener = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) = Unit
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    offer(s)
                }
            }
            addTextChangedListener(listener)
            awaitClose { removeTextChangedListener(listener) }
        }.onStart { emit(text) }
    }


}