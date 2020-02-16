package com.bugeto.data.repository

import com.bugeto.data.api.RemoteNewsApi
import com.bugeto.domain.entities.NewsSourcesEntity
import com.bugeto.data.entities.NewsDataEntityMapper
import io.reactivex.Flowable

class NewsRemoteImpl constructor(private val api: RemoteNewsApi): NewsDataStore {

    private val newsMapper = NewsDataEntityMapper()

    override fun getNews(): Flowable<NewsSourcesEntity> {

        return api.getNews().map { newsMapper.mapToEntity(it) }
    }

}