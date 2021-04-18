package com.example.marval.ui.search

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.AccelerateInterpolator
import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.lifecycle.Observer
import com.example.marval.R
import com.example.marval.model.main.Result
import com.example.marval.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import org.koin.androidx.viewmodel.ext.android.viewModel


const val EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X"
const val EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y"

var rootLayout: View? = null

private var revealX = 0
private var revealY = 0

class SearchActivity : BaseActivity() {
    private val searchVM: SearchVM by viewModel()
    lateinit var adapter: SearchAdapter
    override fun getActivityView(): Int {
        return R.layout.activity_search
    }

    override fun afterInflation(savedInstance: Bundle?) {

        setupTransition(savedInstance)
        setupViews()
        setupObserver()
    }

    fun setupTransition(savedInstance: Bundle?) {

        if (savedInstance == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)
        ) {
            rootLayout.visibility = View.INVISIBLE

            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);

            val viewTreeObserver = rootLayout.viewTreeObserver
            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        revealActivity(revealX, revealY)
                        rootLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
            }

        } else {
            rootLayout.visibility = View.VISIBLE
        }
    }

    override fun onBackPressed() {
        unRevealActivity()
        super.onBackPressed()
    }

    protected fun revealActivity(x: Int, y: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val finalRadius = (Math.max(
                rootLayout!!.width,
                rootLayout!!.height
            ) * 1.1).toFloat()

            // create the animator for this view (the start radius is zero)
            val circularReveal: Animator =
                ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0f, finalRadius)
            circularReveal.setDuration(400)
            circularReveal.setInterpolator(AccelerateInterpolator())

            // make the view visible and start the animation
            rootLayout!!.visibility = View.VISIBLE
            circularReveal.start()
        } else {
            finish()
        }
    }

    protected fun unRevealActivity() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            finish()
        } else {
            val finalRadius = (Math.max(
                rootLayout!!.width,
                rootLayout!!.height
            ) * 1.1).toFloat()
            val circularReveal: Animator = ViewAnimationUtils.createCircularReveal(
                rootLayout, revealX, revealY, finalRadius, 0f
            )
            circularReveal.setDuration(400)
            circularReveal.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    rootLayout!!.visibility = View.INVISIBLE
                    finish()
                }
            })
            circularReveal.start()
        }
    }

    fun setupViews() {
        cancel_txt.setOnClickListener(View.OnClickListener {
            onBackPressed()
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
        adapter = SearchAdapter(search_box.text.trim().toString(), results);
        search_recyclerview.adapter = adapter
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