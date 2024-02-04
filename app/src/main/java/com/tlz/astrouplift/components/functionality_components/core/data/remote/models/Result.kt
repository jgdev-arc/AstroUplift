package com.tlz.astrouplift.components.functionality_components.core.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("events")
    val events: List<String> = listOf(),
    @SerialName("featured")
    val featured: Boolean = false,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("image_url")
    val imageUrl: String = "",
    @SerialName("launches")
    val launches: List<String> = listOf(),
    @SerialName("news_site")
    val newsSite: String = "",
    @SerialName("published_at")
    val publishedAt: String = "",
    @SerialName("summary")
    val summary: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("updated_at")
    val updatedAt: String = "",
    @SerialName("url")
    val url: String = ""
)