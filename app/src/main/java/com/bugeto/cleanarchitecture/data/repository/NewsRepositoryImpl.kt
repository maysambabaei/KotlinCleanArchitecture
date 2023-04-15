package com.bugeto.cleanarchitecture.data.repository

import com.bugeto.cleanarchitecture.domain.entities.NewsSourcesEntity
import com.bugeto.cleanarchitecture.domain.repositories.NewsRepository
import io.reactivex.Flowable

class NewsRepositoryImpl(private val remote: NewsRemoteImpl,
                         private val cache: NewsCacheImpl) : NewsRepository {

    override fun getLocalNews(): Flowable<NewsSourcesEntity> {
        return cache.getNews()
    }

    override fun getRemoteNews(): Flowable<NewsSourcesEntity> {
        return remote.getNews()
    }

    override fun getNews(): Flowable<NewsSourcesEntity> {
        val updateNewsFlowable = remote.getNews()
        return cache.getNews()
            .mergeWith(updateNewsFlowable.doOnNext{
                    remoteNews -> cache.saveArticles(remoteNews)
            })
    }
}