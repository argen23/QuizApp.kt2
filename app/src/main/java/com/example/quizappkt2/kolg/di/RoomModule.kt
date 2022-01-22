package com.example.quizappkt2.kolg.di

import android.content.Context
import androidx.room.Room
import com.example.quizappkt2.kolg.data.local.AppDatabase
import com.example.quizappkt2.kolg.data.local.HistoryDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun getHistoryDao(appDatabase: AppDatabase): HistoryDao {
        return appDatabase.historyDao()
    }

}