package com.bugeto.cleanarchitecture.data.repository

import com.bugeto.cleanarchitecture.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable

interface NewsDataStore {
    fun getNews(): Flowable<NewsSourcesEntity>
}