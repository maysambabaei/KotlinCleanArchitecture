package com.bugeto.domain.usecases

import com.bugeto.domain.common.BaseFlowableUseCase
import com.bugeto.domain.common.FlowableRxTransformer
import com.bugeto.domain.entities.NewsSourcesEntity
import com.bugeto.domain.repositories.NewsRepository
import io.reactivex.Flowable

//It will get you the only the latest by fetching it from remote
class GetRemoteNewsUseCase(private val transformer: FlowableRxTransformer<NewsSourcesEntity>,
                           private val repositories: NewsRepository): BaseFlowableUseCase<NewsSourcesEntity>(transformer){

    companion object {
        private const val PARAM_FILE_NEWS_ENTITY = "param:NewsStatus"
    }

    override fun createFlowable(data: Map<String, Any>?): Flowable<NewsSourcesEntity> {
        return repositories.getNews()
    }

    fun getNews(): Flowable<NewsSourcesEntity>{
        val data = HashMap<String, String>()
        return single(data)
    }


}