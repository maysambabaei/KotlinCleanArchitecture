package com.bugeto.cleanarchitecture.presentation.mappers

import com.bugeto.cleanarchitecture.domain.common.Mapper
import com.bugeto.cleanarchitecture.domain.entities.NewsPublisherEntity
import com.bugeto.cleanarchitecture.domain.entities.NewsSourcesEntity
import com.bugeto.cleanarchitecture.presentation.entities.NewsPublisher
import com.bugeto.cleanarchitecture.presentation.entities.NewsSources

class NewsEntityMapper : Mapper<NewsSourcesEntity, NewsSources>() {
    override fun mapFrom(data: NewsSourcesEntity): NewsSources = NewsSources(
        status = data?.status,
        articles = mapListArticlesToPresentation(data?.articles)
    )

    private fun mapListArticlesToPresentation(articles: List<NewsPublisherEntity>?)
            : List<NewsPublisher> = articles?.map { mapArticleToPresentation(it) }
        ?: emptyList()

    private fun mapArticleToPresentation(response: NewsPublisherEntity): NewsPublisher = NewsPublisher(
        id = response.id,
        name = response.name,
        description = response.description,
        url = response.url,
        category = response.category
    )

}