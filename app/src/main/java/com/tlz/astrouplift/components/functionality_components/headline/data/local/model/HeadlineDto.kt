package com.tlz.astrouplift.components.functionality_components.headline.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.tlz.astrouplift.components.functionality_components.core.data.local.LocalContractDto

@Entity(tableName = "headline_table")
data class HeadlineDto(
    override val id: Int,
    override val author: String,
    override val content: String,
    override val description: String,
    @ColumnInfo("published_at")
    override val publishedAt: String,
    override val source: String,
    override val title: String,
    override val url: String,
    @ColumnInfo("url_to_image")
    override val urlToImage: String?,
    override val favorite: Boolean,
    override val category: String,
    override val page: Int,

): LocalContractDto()
