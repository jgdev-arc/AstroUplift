package com.tlz.astrouplift.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tlz.astrouplift.components.functionality_components.core.data.local.AstroUpliftArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AstroUpliftLocalModule {

    @Singleton
    @Provides
    fun provideAstroUpliftDatabase(@ApplicationContext context: Context
    ): AstroUpliftArticleDatabase {
        return Room.databaseBuilder(
            context,
            AstroUpliftArticleDatabase::class.java,
            "astro_db"
        )
            .build()
    }
}