package com.tlz.astrouplift.components.functionality_components.headline.domain.use_cases

import com.tlz.astrouplift.components.functionality_components.core.domain.AstroUpliftArticle
import com.tlz.astrouplift.components.functionality_components.headline.domain.repository.HeadlineRepository

class UpdateHeadlineFavoriteUseCase(
    private val repository: HeadlineRepository
) {
    suspend operator fun invoke(article: AstroUpliftArticle) {
        repository.updateFavoriteArticle(article)
    }
}