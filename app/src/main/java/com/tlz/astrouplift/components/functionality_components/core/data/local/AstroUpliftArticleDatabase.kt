package com.tlz.astrouplift.components.functionality_components.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tlz.astrouplift.components.functionality_components.headline.data.local.dao.HeadlineDao
import com.tlz.astrouplift.components.functionality_components.headline.data.local.dao.HeadlineRemoteKeyDao
import com.tlz.astrouplift.components.functionality_components.headline.data.local.model.HeadlineDto
import com.tlz.astrouplift.components.functionality_components.headline.data.local.model.HeadlineRemoteKey

@Database(
    entities = [
        HeadlineDto::class,
        HeadlineRemoteKey::class
   ],
    exportSchema = false,
    version = 1
)
abstract class AstroUpliftArticleDatabase: RoomDatabase() {
    abstract fun headlineDao(): HeadlineDao
    abstract fun headlineRemoteDao(): HeadlineRemoteKeyDao
}