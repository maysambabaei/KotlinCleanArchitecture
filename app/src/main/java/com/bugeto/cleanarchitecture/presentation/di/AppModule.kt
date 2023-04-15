package com.bugeto.cleanarchitecture.presentation.di

import androidx.room.Room
import com.bugeto.cleanarchitecture.data.api.RemoteNewsApi
import com.bugeto.cleanarchitecture.data.db.NewsDatabase
import com.bugeto.cleanarchitecture.data.entities.NewsDataEntityMapper
import com.bugeto.cleanarchitecture.data.entities.NewsEntityDataMapper
import com.bugeto.cleanarchitecture.data.repository.NewsCacheImpl
import com.bugeto.cleanarchitecture.data.repository.NewsRemoteImpl
import com.bugeto.cleanarchitecture.data.repository.NewsRepositoryImpl
import com.bugeto.cleanarchitecture.domain.usecases.GetNewsUseCase
import com.bugeto.cleanarchitecture.presentation.common.AsyncFlowableTransformer
import com.bugeto.cleanarchitecture.presentation.mappers.NewsEntityMapper
import com.bugeto.cleanarchitecture.presentation.ui.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit


val mRepositoryModules = module {
    single(named("remote")) { NewsRemoteImpl(api = get(named(API))) }
    single(named("local")) {
        NewsCacheImpl(database = get(named(DATABASE)), entityToDataMapper = NewsEntityDataMapper(),
            dataToEntityMapper = NewsDataEntityMapper()
        )
    }
    single { NewsRepositoryImpl(remote = get(named("remote")), cache = get(named("local"))) }


}

val mUseCaseModules = module {
    factory(named("getNewsUseCase")) { GetNewsUseCase(transformer = AsyncFlowableTransformer(), repositories = get()) }
}

val mNetworkModules = module {
    single(named(RETROFIT_INSTANCE)) { createNetworkClient(BASE_URL) }
    single(named(API)) { (get(named(RETROFIT_INSTANCE)) as Retrofit).create(RemoteNewsApi::class.java) }
}

val mLocalModules = module {
    single(named(DATABASE)) { Room.databaseBuilder(androidContext(), NewsDatabase::class.java, "news_articles").build() }}

val mViewModels = module {
    viewModel {
        NewsViewModel(getNewsUseCase = get(named(GET_NEWS_USECASE)), mapper = NewsEntityMapper())
    }
}

private const val BASE_URL = "https://newsapi.org/v2/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_NEWS_USECASE = "getNewsUseCase"
private const val DATABASE = "database"