package com.tlz.astrouplift.components.functionality_components.headline.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.tlz.astrouplift.components.functionality_components.core.data.local.AstroUpliftArticleDatabase
import com.tlz.astrouplift.components.functionality_components.core.data.remote.models.Article
import com.tlz.astrouplift.components.functionality_components.core.domain.AstroUpliftArticle
import com.tlz.astrouplift.components.functionality_components.core.domain.mapper.Mapper
import com.tlz.astrouplift.components.functionality_components.headline.data.local.model.HeadlineDto
import com.tlz.astrouplift.components.functionality_components.headline.data.paging.HeadlineRemoteMediator
import com.tlz.astrouplift.components.functionality_components.headline.data.remote.HeadlineApi
import com.tlz.astrouplift.components.functionality_components.headline.domain.repository.HeadlineRepository
import com.tlz.astrouplift.utils.K
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HeadlineRepositoryImpl(
    private val headlineApi: HeadlineApi,
    private val database: AstroUpliftArticleDatabase,
    private val mapper: Mapper<HeadlineDto, AstroUpliftArticle>,
    private val articleHeadlineMapper: Mapper<Article, HeadlineDto>
): HeadlineRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun fetchHeadlineArticle(
        category: String,
        country: String,
        language: String
    ): Flow<PagingData<AstroUpliftArticle>> {
        return Pager(
            PagingConfig(
                pageSize = K.PAGE_SIZE,
                prefetchDistance = K.PAGE_SIZE - 1,
                initialLoadSize = 10
            ),
            remoteMediator = HeadlineRemoteMediator(
                api = headlineApi,
                database = database,
                category = category,
                country = country,
                language = language,
                articleHeadlineDtoMapper = articleHeadlineMapper
            )
        ) {
            database.headlineDao().getAllHeadlineArticles()
        }.flow.map { dtoPager ->
            dtoPager.map { dto ->
                mapper.toModel(dto)
            }
        }
    }
    override suspend fun updateFavoriteArticle(astroUpliftArticle: AstroUpliftArticle) {
        database.headlineDao().updateFavoriteArticle(
            isFavorite = astroUpliftArticle.favorite,
            id = astroUpliftArticle.id
        )
    }
}


