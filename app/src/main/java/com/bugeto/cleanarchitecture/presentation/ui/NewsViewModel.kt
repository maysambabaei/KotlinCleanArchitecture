package com.bugeto.cleanarchitecture.presentation.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bugeto.cleanarchitecture.domain.common.Mapper
import com.bugeto.cleanarchitecture.domain.entities.NewsSourcesEntity
import com.bugeto.cleanarchitecture.domain.usecases.GetNewsUseCase
import com.bugeto.cleanarchitecture.presentation.common.BaseViewModel
import com.bugeto.cleanarchitecture.presentation.entities.Data
import com.bugeto.cleanarchitecture.presentation.entities.Error
import com.bugeto.cleanarchitecture.presentation.entities.NewsSources
import com.bugeto.cleanarchitecture.presentation.entities.Status


class NewsViewModel(private val getNewsUseCase: GetNewsUseCase,
                    private val mapper: Mapper<NewsSourcesEntity, NewsSources>
) : BaseViewModel() {

    companion object {
        private val TAG = "viewmodel"
    }

    var mNews = MutableLiveData<Data<NewsSources>>()

    fun fetchNews() {
        val disposable = getNewsUseCase.getNews()
            .flatMap { mapper.Flowable(it) }
            .subscribe({ response ->
                Log.d(TAG, "On Next Called")
                mNews.value = Data(responseType = Status.SUCCESSFUL, data = response)
            }, { error ->
                Log.d(TAG, "On Error Called")
                mNews.value = Data(responseType = Status.ERROR, error = Error(error.message))
            }, {
                Log.d(TAG, "On Complete Called")
            })

        addDisposable(disposable)
    }

    fun getNewsLiveData() = mNews
}