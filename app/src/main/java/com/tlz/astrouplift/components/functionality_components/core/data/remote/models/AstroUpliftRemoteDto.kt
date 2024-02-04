package com.tlz.astrouplift.components.functionality_components.core.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// THIS FILE MIGHT NOT BE IN THE CORRECT PLACE OR HAVE THE CORRECT CONTENT!!

@Serializable
data class AstroUpliftRemoteDto(
    @SerialName("articles")
    val articles: List<Article> = listOf(),
    @SerialName("status")
    val status: String = "",
    @SerialName("totalResults")
    val totalResults: Int = 0


)