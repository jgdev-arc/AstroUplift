package com.tlz.astrouplift.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tlz.astrouplift.components.functionality_components.core.data.local.AstroUpliftArticleDatabase
import com.tlz.astrouplift.components.functionality_components.core.data.remote.models.Article
import com.tlz.astrouplift.components.functionality_components.core.domain.AstroUpliftArticle
import com.tlz.astrouplift.components.functionality_components.core.domain.mapper.Mapper
import com.tlz.astrouplift.components.functionality_components.headline.data.local.dao.HeadlineDao
import com.tlz.astrouplift.components.functionality_components.headline.data.local.dao.HeadlineRemoteKeyDao
import com.tlz.astrouplift.components.functionality_components.headline.data.local.model.HeadlineDto
import com.tlz.astrouplift.components.functionality_components.headline.data.remote.HeadlineApi
import com.tlz.astrouplift.components.functionality_components.headline.data.repository.HeadlineRepositoryImpl
import com.tlz.astrouplift.components.functionality_components.headline.domain.repository.HeadlineRepository
import com.tlz.astrouplift.components.functionality_components.headline.domain.use_cases.FetchHeadlineArticleUseCase
import com.tlz.astrouplift.components.functionality_components.headline.domain.use_cases.HeadlineUseCases
import com.tlz.astrouplift.components.functionality_components.headline.domain.use_cases.UpdateHeadlineFavoriteUseCase
import com.tlz.astrouplift.utils.K
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeadlineModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideHeadlineApi(): HeadlineApi {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(HeadlineApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHeadlineRepository(
        api: HeadlineApi,
        database: AstroUpliftArticleDatabase,
        mapper: Mapper<HeadlineDto, AstroUpliftArticle>,
        articleHeadlineMapper: Mapper<Article, HeadlineDto>
    ): HeadlineRepository {
        return HeadlineRepositoryImpl(
            headlineApi = api,
            database = database,
            mapper = mapper,
            articleHeadlineMapper = articleHeadlineMapper
        )
    }

    @Provides
    @Singleton
    fun provideHeadlineDao(
        database: AstroUpliftArticleDatabase
    ): HeadlineDao = database.headlineDao()

    @Provides
    @Singleton
    fun provideRemoteKeyDao(
        database: AstroUpliftArticleDatabase
    ): HeadlineRemoteKeyDao = database.headlineRemoteDao()

    @Provides
    @Singleton
    fun provideHeadlineUseCases(
        repository: HeadlineRepository
    ): HeadlineUseCases =
        HeadlineUseCases(
            fetchHeadlineArticleUseCase = FetchHeadlineArticleUseCase(
                repository = repository
            ),
            updateHeadlineFavoriteUseCase = UpdateHeadlineFavoriteUseCase(
                repository = repository
            )
        )

}