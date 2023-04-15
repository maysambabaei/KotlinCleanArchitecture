package com.bugeto.cleanarchitecture.data.repository

import com.bugeto.cleanarchitecture.data.db.NewsDao
import com.bugeto.cleanarchitecture.data.db.NewsDatabase
import com.bugeto.cleanarchitecture.data.entities.NewsDataEntityMapper
import com.bugeto.cleanarchitecture.data.entities.NewsEntityDataMapper
import com.bugeto.cleanarchitecture.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable

class NewsCacheImpl (private val database: NewsDatabase,
                     private val entityToDataMapper: NewsEntityDataMapper,
                     private val dataToEntityMapper: NewsDataEntityMapper
) : NewsDataStore {

    private val dao: NewsDao = database.getNewsDao()

    override fun getNews(): Flowable<NewsSourcesEntity> {
        return dao.getAllArticles().map { it ->
            dataToEntityMapper.mapToEntity(it)
        }
    }

    fun saveArticles(it: NewsSourcesEntity) {
        dao.clear()
        dao.saveAllArticles(it.articles.map { articles -> entityToDataMapper.mapArticleToEntity(articles) })
    }

}