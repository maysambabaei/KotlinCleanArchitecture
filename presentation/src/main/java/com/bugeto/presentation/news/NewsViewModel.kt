package com.bugeto.presentation.news

import androidx.lifecycle.MutableLiveData
import android.util.Log
import com.bugeto.domain.common.Mapper
import com.bugeto.domain.entities.NewsSourcesEntity
import com.bugeto.domain.usecases.GetNewsUseCase
import com.bugeto.presentation.common.BaseViewModel
import com.bugeto.presentation.entities.Data
import com.bugeto.presentation.entities.Error
import com.bugeto.presentation.entities.NewsSources
import com.bugeto.presentation.entities.Status

class NewsViewModel(private val getNewsUseCase: GetNewsUseCase,
                    private val mapper: Mapper<NewsSourcesEntity, NewsSources>) : BaseViewModel() {

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