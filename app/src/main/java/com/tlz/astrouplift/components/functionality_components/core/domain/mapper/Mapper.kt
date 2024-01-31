package com.tlz.astrouplift.components.functionality_components.core.domain.mapper

interface Mapper<T: Any?, Model: Any>{
    fun toModel(value: T): Model
    fun fromModel(value: Model): T
}