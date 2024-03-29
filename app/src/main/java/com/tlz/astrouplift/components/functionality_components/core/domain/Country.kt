package com.tlz.astrouplift.components.functionality_components.core.domain

import androidx.annotation.DrawableRes

data class Country(
    val code: String,
    val name: String,
    @DrawableRes val icResId: Int,
)