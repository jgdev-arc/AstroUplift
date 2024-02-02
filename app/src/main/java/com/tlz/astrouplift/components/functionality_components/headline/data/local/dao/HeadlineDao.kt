package com.tlz.astrouplift.components.functionality_components.headline.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tlz.astrouplift.components.functionality_components.headline.data.local.model.HeadlineDto

@Dao
interface HeadlineDao {
    @Query("SELECT * FROM headline_table")
    fun getAllHeadlineArticles(): PagingSource<Int, HeadlineDto>

    @Query("SELECT * FROM headline_table WHERE id =:id")
    fun getHeadlineArticle(id: Int): kotlinx.coroutines.flow.Flow<HeadlineDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeadlineArticle(articles: List<HeadlineDto>)

    @Query(
        "DELETE FROM headline_table WHERE favorite=0"
    )
    suspend fun removeAllHeadlineArticles()

    @Delete
    suspend fun removeFavoriteArticle(headlineDto: HeadlineDto)

    @Query(
        "UPDATE headline_table SET favorite=:isFavorite WHERE id=:id"
    )
    suspend fun updateFavoriteArticle(isFavorite: Boolean, id: Int)
}