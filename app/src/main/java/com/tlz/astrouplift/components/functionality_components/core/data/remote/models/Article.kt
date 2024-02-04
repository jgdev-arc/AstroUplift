package com.tlz.astrouplift.components.functionality_components.core.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.xml.transform.Source

@Serializable
data class Article(
    @SerialName("author")
    val author: String? = null,
    @SerialName("content")
    val content: String = "",
    @SerialName("description")
    val description: String? = null,
    @SerialName("publishedAt")
    val publishedAt: String = "",
    @SerialName("source")
    val source: com.tlz.astrouplift.components.functionality_components.core.data.remote.models.Source = Source(),
    @SerialName("title")
    val title: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("urlToImage")
    val urlToImage: String? = null
)