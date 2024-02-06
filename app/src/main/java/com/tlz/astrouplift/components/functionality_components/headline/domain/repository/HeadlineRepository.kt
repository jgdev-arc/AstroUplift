package com.tlz.astrouplift.components.functionality_components.headline.domain.repository

import androidx.paging.PagingData
import com.tlz.astrouplift.components.functionality_components.core.domain.AstroUpliftArticle
import com.tlz.astrouplift.components.functionality_components.core.domain.Language
import kotlinx.coroutines.flow.Flow

interface HeadlineRepository {
    fun fetchHeadlineArticle(
        category: String,
        language: String
    ) : Flow<PagingData<AstroUpliftArticle>>

    suspend fun updateFavoriteArticle(astroUpliftArticle: AstroUpliftArticle)
}