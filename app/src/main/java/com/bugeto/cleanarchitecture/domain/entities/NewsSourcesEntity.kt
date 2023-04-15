package com.bugeto.cleanarchitecture.domain.entities

data class NewsSourcesEntity(
    var status: String? = null,
    var articles: List<NewsPublisherEntity> = emptyList()

)