package com.example.marval.di

import com.example.marval.network.AppRepository
import com.example.marval.ui.base.viewmodel.CommanVM
import com.example.marval.ui.details.DetailsVM
import com.example.marval.ui.main.MainVM
import com.example.marval.ui.search.SearchVM
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Hero is the appModule for koin used to define view models && inject singleton class's objects
 * @author Mohamed Ibrahim
 */
val appModule = module {

    //repos
    factory { AppRepository() }


    //viewmodel
    viewModel { CommanVM(get()) }
    viewModel { MainVM(get()) }
    viewModel { SearchVM(get()) }
    viewModel { DetailsVM(get()) }
}