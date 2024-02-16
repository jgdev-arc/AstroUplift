package com.tlz.astrouplift.features_presentation.home.view_model

import com.tlz.astrouplift.components.functionality_components.core.domain.AstroUpliftArticle
import com.tlz.astrouplift.utils.ArticleCategory

sealed class HomeUIEvents {
    object ViewMoreClicked: HomeUIEvents()
    data class ArticleClicked(val url: String): HomeUIEvents()
    data class CategoryChange(val category: ArticleCategory): HomeUIEvents()
    data class PreferencePanelToggle(val isOpen: Boolean): HomeUIEvents()
    data class OnHeadlineFavoriteChange(val article: AstroUpliftArticle): HomeUIEvents()
}
