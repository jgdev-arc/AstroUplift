package com.tlz.astrouplift.features_presentation.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tlz.astrouplift.components.functionality_components.core.domain.AstroUpliftArticle
import com.tlz.astrouplift.features_presentation.core.components.HeaderTitle
import com.tlz.astrouplift.features_presentation.core.components.itemSpacing
import com.tlz.astrouplift.features_presentation.home.components.HomeTopAppBar
import com.tlz.astrouplift.features_presentation.home.view_model.HomeViewModel
import com.tlz.astrouplift.utils.ArticleCategory
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onViewMoreClicked: () -> Unit,
    onHeadlineItemClicked: () -> Unit,
    onSearchClick: () -> Unit,
    openDrawer: () -> Unit
) {
    val homeState = viewModel.homeState
    val headlineArticles = homeState.headlineArticles.collectAsLazyPagingItems()
    val categories = ArticleCategory.values()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        topBar = {
            HomeTopAppBar(
                openDrawer = openDrawer,
                onSearch = onSearchClick
            )
        }
    ) {innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {

        }
    }
}


private fun LazyListScope.HeadlineItems(
    headlineArticles: LazyPagingItems<AstroUpliftArticle>,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    onViewMoreClick: () -> Unit,
    onHeadlineItemClick: (id: Int) -> Unit,
    onFavoriteHeadlineChange: (AstroUpliftArticle) -> Unit
) {
    item {
        HeaderTitle(
            title = "Hot News",
            icon = Icons.Default.LocalFireDepartment
        )
        Spacer(modifier = Modifier.size(itemSpacing))
    }

    item {

    }

}