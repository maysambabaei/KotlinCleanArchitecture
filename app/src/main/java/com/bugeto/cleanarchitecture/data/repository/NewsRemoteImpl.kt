package com.bugeto.cleanarchitecture.data.repository

import com.bugeto.cleanarchitecture.data.api.RemoteNewsApi
import com.bugeto.cleanarchitecture.data.entities.NewsDataEntityMapper
import com.bugeto.cleanarchitecture.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable

class NewsRemoteImpl constructor(private val api: RemoteNewsApi): NewsDataStore {

    private val newsMapper = NewsDataEntityMapper()

    override fun getNews(): Flowable<NewsSourcesEntity> {

        return api.getNews().map { newsMapper.mapToEntity(it) }
    }

}