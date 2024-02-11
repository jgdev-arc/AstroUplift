package com.tlz.astrouplift.components.functionality_components.headline.domain.use_cases

import androidx.paging.PagingData
import com.tlz.astrouplift.components.functionality_components.core.domain.AstroUpliftArticle
import com.tlz.astrouplift.components.functionality_components.headline.domain.repository.HeadlineRepository
import java.util.concurrent.Flow

class FetchHeadlineArticleUseCase(
    private val repository: HeadlineRepository
) {
    operator fun invoke(
        category: String,
        countryCode: String,
        languageCode: String
    ): kotlinx.coroutines.flow.Flow<PagingData<AstroUpliftArticle>> = repository.fetchHeadlineArticle(
        category, countryCode, languageCode
    )
}