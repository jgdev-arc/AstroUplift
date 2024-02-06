package com.tlz.astrouplift.components.functionality_components.headline.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
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

class HeadlineRepositoryImpl(
    private val headlineApi: HeadlineApi,
    private val database: AstroUpliftArticleDatabase,
    private val mapper: Mapper<HeadlineDto, AstroUpliftArticle>,
    private val articleHeadlineMapper: Mapper<Article, HeadlineDto>
): HeadlineRepository {
    override fun fetchHeadlineArticle(
        category: String,
        language: String
    ): Flow<PagingData<AstroUpliftArticle>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateFavoriteArticle(astroUpliftArticle: AstroUpliftArticle) {
        TODO("Not yet implemented")
    }
    
}


