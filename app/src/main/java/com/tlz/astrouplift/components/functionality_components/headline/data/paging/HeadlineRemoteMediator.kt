package com.tlz.astrouplift.components.functionality_components.headline.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.tlz.astrouplift.components.functionality_components.core.data.local.AstroUpliftArticleDatabase
import com.tlz.astrouplift.components.functionality_components.core.data.remote.models.Article
import com.tlz.astrouplift.components.functionality_components.core.domain.mapper.Mapper
import com.tlz.astrouplift.components.functionality_components.headline.data.local.model.HeadlineDto
import com.tlz.astrouplift.components.functionality_components.headline.data.local.model.HeadlineRemoteKey
import com.tlz.astrouplift.components.functionality_components.headline.data.remote.HeadlineApi
import hoods.com.newsy.features_components.core.data.local.NewsyArticleDatabase
import hoods.com.newsy.features_components.core.data.remote.models.Article
import hoods.com.newsy.features_components.core.domain.mapper.Mapper
import hoods.com.newsy.features_components.headline.data.local.model.HeadlineDto
import hoods.com.newsy.features_components.headline.data.local.model.HeadlineRemoteKey
import hoods.com.newsy.features_components.headline.data.mapper.ArticleHeadlineDtoMapper
import hoods.com.newsy.features_components.headline.data.remote.HeadlineApi
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class HeadlineRemoteMediator(
    private val api: HeadlineApi,
    private val database: AstroUpliftArticleDatabase,
    private val articleHeadlineDtoMapper: Mapper<Article, HeadlineDto>,
    private val category: String = "",
    private val country: String = "",
    private val language: String = ""
): RemoteMediator<Int, HeadlineDto>() {

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(20, TimeUnit.MINUTES)
        return if (
            System.currentTimeMillis() -
            (database.headlineRemoteDao().getCreationTime() ?: 0) < cacheTimeout
        ) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }

    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HeadlineDto>
    ): MediatorResult {
        val page: Int = when(loadType) {
            LoadType.REFRESH -> {}
            LoadType.PREPEND -> {}
            LoadType.APPEND -> {}
        }
        return try {
            val headlineApiResponse = api.getHeadlines(
                pageSize = state.config.pageSize,
                category = category,
                page = page,
                country = country,
                language = language
            )
            val headlineArticles = headlineApiResponse.articles
            val endOfPaginationReached = headlineArticles.isEmpty()
            database.apply {
                if (loadType == LoadType.REFRESH) {
                    database.apply {
                        headlineRemoteDao().clearRemoteKeys()
                        headlineDao().removeAllHeadlineArticles()
                    }
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = headlineArticles.map {
                    HeadlineRemoteKey(
                        articleId = it.url,
                        prevKey = prevKey,
                        nextKey = nextKey,
                        currentPage = page
                    )
                }
                database.apply {
                    headlineRemoteDao().insertAll(remoteKeys)
                    headlineDao().insertHeadlineArticle(
                        articles = headlineArticles.map {
                            articleHeadlineDtoMapper.toModel(
                                it
                            )
                        }
                    )
                }
            }
            MediatorResult.Success(endOfPaginationReached)
        } catch (error: IOException) {
            MediatorResult.Error(error)
        } catch (error: HttpException) {
            MediatorResult.Error(error)
        }
    }
}