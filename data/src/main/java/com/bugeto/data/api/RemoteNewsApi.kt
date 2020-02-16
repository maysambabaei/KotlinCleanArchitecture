package com.bugeto.data.api

import com.rakshitjain.data.BuildConfig
import com.bugeto.data.entities.NewsSourcesData
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET

interface RemoteNewsApi {

    @GET("top-headlines?country=us")
    fun getNews(): Flowable<NewsSourcesData>

}