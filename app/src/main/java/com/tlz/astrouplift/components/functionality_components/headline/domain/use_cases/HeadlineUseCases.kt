package com.tlz.astrouplift.components.functionality_components.headline.domain.use_cases

data class HeadlineUseCases(
    val fetchHeadlineArticleUseCase: FetchHeadlineArticleUseCase,
    val updateHeadlineFavoriteUseCase: UpdateHeadlineFavoriteUseCase
) {
}