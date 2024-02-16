package com.tlz.astrouplift.features_presentation.home.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tlz.astrouplift.components.functionality_components.headline.domain.use_cases.HeadlineUseCases
import com.tlz.astrouplift.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val headlineUseCases: HeadlineUseCases
): ViewModel() {
    var homeState by mutableStateOf(HomeState())
        private set

    init {
        loadArticles()
    }

    private fun loadArticles() {
        homeState = homeState.copy(
            headlineArticles = headlineUseCases.fetchHeadlineArticleUseCase(
                category = homeState.selectedHeadlineCategory.category,
                countryCode = Utils.countryCodeList[0].code,
                languageCode = Utils.languageCodeList[0].code
            ).cachedIn(viewModelScope)
        )
    }


    fun onHomeUIEvents(homeUIEvents: HomeUIEvents) {
        when (homeUIEvents) {
            HomeUIEvents.ViewMoreClicked -> {}
            is HomeUIEvents.ArticleClicked -> {}
            is HomeUIEvents.CategoryChange -> {}
            is HomeUIEvents.PreferencePanelToggle -> {}
            is HomeUIEvents.OnHeadlineFavoriteChange -> {
                viewModelScope.launch {
                    val isFavorite = homeUIEvents.article.favorite
                    val update = homeUIEvents.article.copy(
                        favorite = !isFavorite
                    )
                    headlineUseCases.updateHeadlineFavoriteUseCase(
                        update
                    )
                }
            }

        }
    }
}