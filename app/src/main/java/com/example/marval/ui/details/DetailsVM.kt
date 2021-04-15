package com.example.marval.ui.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.marval.model.GenericResponse
import com.example.marval.model.main.ChractersListModel
import com.example.marval.model.resource.ResourceModel
import com.example.marval.network.AppRepository
import com.example.marval.network.retrofit.CallbackWrapper
import com.example.marval.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val TAG = "DetailsVM"

class DetailsVM(private val repository: AppRepository) : BaseViewModel() {


    var comicsList: MutableLiveData<ResourceModel> = MutableLiveData()
    var eventsList: MutableLiveData<ResourceModel> = MutableLiveData()
    var seriesList: MutableLiveData<ResourceModel> = MutableLiveData()
    var storiesList: MutableLiveData<ResourceModel> = MutableLiveData()

    fun getComicsList(characterId: String) {
        loading.value = true
        mCompositeDisposable.add(
            repository.getComicsList(characterId = characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    CallbackWrapper<GenericResponse<ResourceModel>>() {
                    override fun onSuccess(t: GenericResponse<ResourceModel>) {
                        loading.value = false
                        comicsList.postValue(t.data)
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

    fun getEventsList(characterId: String) {
        loading.value = true
        mCompositeDisposable.add(
            repository.getEventsList(characterId = characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    CallbackWrapper<GenericResponse<ResourceModel>>() {
                    override fun onSuccess(t: GenericResponse<ResourceModel>) {
                        loading.value = false
                        eventsList.postValue(t.data)
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

    fun getSeriesList(characterId: String) {
        loading.value = true
        mCompositeDisposable.add(
            repository.getSeriesList(characterId = characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    CallbackWrapper<GenericResponse<ResourceModel>>() {
                    override fun onSuccess(t: GenericResponse<ResourceModel>) {
                        loading.value = false
                        seriesList.postValue(t.data)
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

    fun getStoriesList(characterId: String) {
        loading.value = true
        mCompositeDisposable.add(
            repository.getStoriesList(characterId = characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    CallbackWrapper<GenericResponse<ResourceModel>>() {
                    override fun onSuccess(t: GenericResponse<ResourceModel>) {
                        loading.value = false
                        storiesList.postValue(t.data)
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