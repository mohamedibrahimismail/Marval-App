package com.example.marval.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.marval.model.GenericResponse
import com.example.marval.model.main.ChractersListModel
import com.example.marval.network.AppRepository
import com.example.marval.network.retrofit.CallbackWrapper
import com.example.marval.ui.base.BaseViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val TAG = "MainVM"

class SearchVM(private val repository: AppRepository) : BaseViewModel() {

    private val autoCompletePublishSubject = PublishRelay.create<String>()

    var charactersList: MutableLiveData<ChractersListModel> = MutableLiveData()

    init {
        configureAutoComplete()
    }

    private fun configureAutoComplete() {
        autoCompletePublishSubject
            .debounce(100, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                repository.getSerchedList(100, 0, nameStartsWith = it).subscribeOn(Schedulers.io())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                charactersList.value = result.data
                loading.value = false
            }, { t: Throwable? -> Timber.w(t, "Failed to get search results") })

    }

    fun getCharactersList(nameStartsWith: String) {
        loading.value = true
        autoCompletePublishSubject.accept(nameStartsWith)
    }


}