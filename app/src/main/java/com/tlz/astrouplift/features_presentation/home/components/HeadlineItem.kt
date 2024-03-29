package com.tlz.astrouplift.features_presentation.home.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tlz.astrouplift.R
import com.tlz.astrouplift.components.functionality_components.core.domain.AstroUpliftArticle
import com.tlz.astrouplift.features_presentation.core.components.defaultPadding
import com.tlz.astrouplift.features_presentation.core.components.itemPadding
import com.tlz.astrouplift.features_presentation.core.components.itemSpacing
import com.tlz.astrouplift.features_presentation.core.ui.theme.AstroUpliftTheme
import com.tlz.astrouplift.utils.Utils
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeadlineItem(
    article: List<AstroUpliftArticle>,
    articleCount: Int,
    onCardClick: (AstroUpliftArticle) -> Unit,
    onViewMoreClick: () -> Unit,
    onFavoriteChange: (AstroUpliftArticle) -> Unit
) {
    var isAutoScrolling by remember {
        mutableStateOf(true)
    }

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {articleCount}
    )

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(key1 = pagerState.currentPage) {
        if (isDragged) {
            isAutoScrolling = false
        } else {
            isAutoScrolling = true
            delay(500)
            with(pagerState) {
                val target = if (currentPage < articleCount - 1) currentPage + 1 else 0
                scrollToPage(target)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(defaultPadding),
            beyondBoundsPageCount = 0,
            pageSize = PageSize.Fill,
            pageSpacing = itemSpacing
        ) {page ->
            if (isAutoScrolling) {
                AnimatedContent(
                    targetState = page,
                    label = ""
                ) {index ->
                    HeadlineCard(
                        modifier = Modifier,
                        article = article[index],
                        onCardClick = onCardClick,
                        onFavoriteChange = onFavoriteChange
                    )
                }
            } else {
                HeadlineCard(
                    modifier = Modifier,
                    article = article[page],
                    onCardClick = onCardClick,
                    onFavoriteChange = onFavoriteChange
                )
            }
        }
        Spacer(modifier = Modifier.size(2.dp))
        TextButton(onClick = onViewMoreClick) {
            Text("view more")
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HeadlineCard(
    modifier: Modifier = Modifier,
    article: AstroUpliftArticle,
    onCardClick: (AstroUpliftArticle) -> Unit,
    onFavoriteChange: (AstroUpliftArticle) -> Unit
) {
    val imgRequest = ImageRequest.Builder(LocalContext.current)
        .data(article.urlToImage)
        .crossfade(true)
        .build()

    val favoriteIcon = if (article.favorite) Icons.Default.BookmarkAdded else Icons.Default.Bookmark

    Card(
        onClick = {onCardClick(article)},
        modifier = modifier
    ) {
        Column {
            AsyncImage(
                model = imgRequest,
                placeholder = painterResource(R.drawable.ideogram_2_),
                error = painterResource(R.drawable.ideogram_2_),
                contentScale = ContentScale.Crop,
                contentDescription = "news image",
                modifier = Modifier.height(150.dp)
            )

            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(itemPadding)
            )

            Row {
                Column(
                    modifier = Modifier.padding(itemPadding)
                ) {
                    Text(
                        text = article.source,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = Utils.formatPublishedAtDate(article.publishedAt),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                IconButton(onClick = {onFavoriteChange(article)}) {
                    Icon(imageVector = favoriteIcon, contentDescription = "favorite")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevHeadlineItem() {
    val article = listOf(
        AstroUpliftArticle(
            author = "John Doe",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            description = "A short description of the article.",
            publishedAt = "2024-02-19T12:34:56Z",
            source = "Newsy Source",
            title = "Dummy News Title",
            url = "https://example.com",
            urlToImage = "https://example.com/image.jpg",
            id = 1,
            category = "Technology",
            favorite = true,
            page = 1
        ),
        AstroUpliftArticle(
            author = "Jane Smith",
            content = "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            description = "Another short description of the article.",
            publishedAt = "2024-02-20T10:45:30Z",
            source = "Another News Source",
            title = "Another Dummy News Title",
            url = "https://example.com/another",
            urlToImage = "https://example.com/another-image.jpg",
            id = 2,
            category = "Science",
            favorite = true,
            page = 1
        )
        // Add more articles as needed
    )

    AstroUpliftTheme {
        HeadlineItem(
            article,
            articleCount = 2,
            onCardClick = {},
            onViewMoreClick = {},
            onFavoriteChange = {}
        )
    }
}