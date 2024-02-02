package com.tlz.astrouplift.components.functionality_components.headline.data.mapper

import com.tlz.astrouplift.components.functionality_components.core.domain.AstroUpliftArticle
import com.tlz.astrouplift.components.functionality_components.core.domain.mapper.Mapper
import com.tlz.astrouplift.components.functionality_components.headline.data.local.model.HeadlineDto

class HeadlineMapper: Mapper<HeadlineDto, AstroUpliftArticle> {
    override fun toModel(value: HeadlineDto): AstroUpliftArticle {
        return value.run {
            AstroUpliftArticle(
                id = id,
                author = author,
                content = content,
                description = description,
                publishedAt = publishedAt,
                source = source,
                title = title,
                url = url,
                urlToImage = urlToImage,
                favorite = favorite,
                category = category,
                page = page
            )
        }
    }

    override fun fromModel(value: AstroUpliftArticle): HeadlineDto {
        return value.run {
            HeadlineDto(
                id = id,
                author = author,
                content = content,
                description = description,
                publishedAt = publishedAt,
                source = source,
                title = title,
                url = url,
                urlToImage = urlToImage,
                favorite = favorite,
                category = category,
                page = page
            )
        }
    }
}