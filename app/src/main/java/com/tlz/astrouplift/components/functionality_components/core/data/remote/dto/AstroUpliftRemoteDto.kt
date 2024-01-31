package com.tlz.astrouplift.components.functionality_components.core.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AstroUpliftRemoteDto(
    @SerialName("count")
    val count: Int = 0,
    @SerialName("next")
    val next: String = "",
    @SerialName("previous")
    val previous: String? = null,
    @SerialName("results")
    val results: List<Result> = listOf()
)