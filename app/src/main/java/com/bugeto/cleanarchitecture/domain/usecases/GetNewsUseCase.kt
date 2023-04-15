package com.bugeto.cleanarchitecture.domain.usecases

import com.bugeto.cleanarchitecture.domain.common.BaseFlowableUseCase
import com.bugeto.cleanarchitecture.domain.common.FlowableRxTransformer
import com.bugeto.cleanarchitecture.domain.entities.NewsSourcesEntity
import com.bugeto.cleanarchitecture.domain.repositories.NewsRepository
import io.reactivex.Flowable

class GetNewsUseCase(private val transformer: FlowableRxTransformer<NewsSourcesEntity>,
                     private val repositories: NewsRepository
): BaseFlowableUseCase<NewsSourcesEntity>(transformer){

    override fun createFlowable(data: Map<String, Any>?): Flowable<NewsSourcesEntity> {
        return repositories.getNews()
    }


    fun getNews(): Flowable<NewsSourcesEntity> {
        val data = HashMap<String, String>()
        return single(data)
    }
}