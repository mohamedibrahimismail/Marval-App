package com.example.marval.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.marval.model.GenericResponse
import com.example.marval.model.main.ServiceModel
import com.example.marval.model.main.SliderModel
import com.example.marval.network.AppRepository
import com.example.marval.network.retrofit.CallbackWrapper
import com.example.marval.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainVM(private val repository: AppRepository) : BaseViewModel() {


    var sliders: MutableLiveData<MutableList<SliderModel>> = MutableLiveData()
    var services: MutableLiveData<MutableList<ServiceModel>> = MutableLiveData()



    fun getSliders() {
        loading.value = true
        mCompositeDisposable.add(
            repository.getMainSliders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    CallbackWrapper<GenericResponse<MutableList<SliderModel>>>() {
                    override fun onSuccess(t: GenericResponse<MutableList<SliderModel>>) {
                        loading.value = false
                        sliders.postValue(t.data)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        loading.value = false
                    }

                    override fun onFail(t: String?) {
                        loading.value = false
                        //kotlin.error.postValue(t)
                    }

                    override fun onFail(t: Map<String, ArrayList<String>>) {
                        loading.value = false
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



    fun getServices() {
        loading.value = true
        mCompositeDisposable.add(
            repository.getServices()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    CallbackWrapper<GenericResponse<MutableList<ServiceModel>>>() {
                    override fun onSuccess(t: GenericResponse<MutableList<ServiceModel>>) {
                        loading.value = false
                        services.postValue(t.data)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        loading.value = false
                    }

                    override fun onFail(t: String?) {
                        loading.value = false
                        //kotlin.error.postValue(t)
                    }

                    override fun onFail(t: Map<String, ArrayList<String>>) {
                        loading.value = false
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