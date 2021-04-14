package com.example.marval.ui.main

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

class MainVM(private val repository: AppRepository) : BaseViewModel() {

    var limit = 5
    var offset = 0
    var charactersList: MutableLiveData<ChractersListModel> = MutableLiveData()
    var paginationLoaderStatus: MutableLiveData<Boolean> = MutableLiveData()


    fun getCharactersList() {
        if(offset==0) {
            loading.value = true
        }else{
            paginationLoaderStatus.value = true
        }
        mCompositeDisposable.add(
            repository.getChractersList(limit = limit, offset = offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    CallbackWrapper<GenericResponse<ChractersListModel>>() {
                    override fun onSuccess(t: GenericResponse<ChractersListModel>) {
                        loading.value = false
                        paginationLoaderStatus.value = false
                        offset += limit
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
//                        t.forEach {
//                            if (it.key == "email") {
//                                emailErrors.value = it.value
//                            } else if (it.key == "password") {
//                                passwordErrors.value = it.value
//                            }else if (it.key == "mobile_number") {
//                                phoneErrors.value = it.value
//                            }
//                        }
                    }
                })
        )
    }


}