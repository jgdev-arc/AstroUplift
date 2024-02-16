package com.tlz.astrouplift.features_presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.tlz.astrouplift.features_presentation.home.components.HomeTopAppBar
import com.tlz.astrouplift.features_presentation.home.view_model.HomeViewModel
import com.tlz.astrouplift.utils.ArticleCategory

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
