package com.bugeto.data.repository

import com.bugeto.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable
import io.reactivex.Single


interface NewsDataStore{
    fun getNews(): Flowable<NewsSourcesEntity>
}