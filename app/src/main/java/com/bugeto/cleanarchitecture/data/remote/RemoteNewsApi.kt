package com.bugeto.cleanarchitecture.data.api

import com.bugeto.cleanarchitecture.data.entities.NewsSourcesData
import io.reactivex.Flowable
import retrofit2.http.GET

interface RemoteNewsApi {
    @GET("top-headlines?country=us")
    fun getNews(): Flowable<NewsSourcesData>
}