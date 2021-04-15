package com.example.marval.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.marval.model.GenericResponse
import com.example.marval.model.main.ChractersListModel
import com.example.marval.network.AppRepository
import com.example.marval.network.retrofit.CallbackWrapper
import com.example.marval.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val TAG = "MainVM"

class SearchVM(private val repository: AppRepository) : BaseViewModel() {


    var charactersList: MutableLiveData<ChractersListModel> = MutableLiveData()


    fun getCharactersList() {
        loading.value = true
        mCompositeDisposable.add(
            repository.getSerchedList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    CallbackWrapper<GenericResponse<ChractersListModel>>() {
                    override fun onSuccess(t: GenericResponse<ChractersListModel>) {
                        loading.value = false
                        charactersList.postValue(t.data)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        loading.value = false
                        Log.e(TAG, "onError: " + e.message)
                    }

                    override fun onFail(t: String?) {
                        loading.value = false
                        //kotlin.error.postValue(t)
                        Log.e(TAG, "onFail: " + t)
                    }

                    override fun onFail(t: Map<String, ArrayList<String>>) {
                        loading.value = false
                        Log.e(TAG, "onFail: " + t)

                    }
                })
        )
    }


}