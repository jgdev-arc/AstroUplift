package com.tlz.astrouplift.components.functionality_components.headline.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.tlz.astrouplift.components.functionality_components.core.data.local.AstroUpliftArticleDatabase
import com.tlz.astrouplift.components.functionality_components.headline.data.local.model.HeadlineDto
import com.tlz.astrouplift.components.functionality_components.headline.data.remote.HeadlineApi
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class HeadlineRemoteMediator(
    private val api: HeadlineApi,
    private val database: AstroUpliftArticleDatabase,
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
        TODO("Not yet implemented")
    }
}