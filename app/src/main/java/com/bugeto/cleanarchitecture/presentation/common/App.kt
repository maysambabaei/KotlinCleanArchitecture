package com.bugeto.cleanarchitecture.presentation.common

import android.app.Application
import com.bugeto.cleanarchitecture.presentation.di.*
import org.koin.core.context.GlobalContext.startKoin


class App :Application() {
    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin{
            modules(listOf(mNetworkModules,
                mViewModels,
                mRepositoryModules,
                mUseCaseModules,
                mLocalModules))
        }
    }
}