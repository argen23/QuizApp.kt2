package com.example.quizappkt2.kolg.di

import android.content.Context
import androidx.room.Room
import com.example.quizappkt2.kolg.data.local.AppDatabase
import com.example.quizappkt2.kolg.data.local.HistoryDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

var roomModule = module {
    single { getAppDatabase(androidContext()) }
    single { getHistoryDao(get()) }
}

private fun getAppDatabase(applicationContext: Context) =
    Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "Quiz Database"
    ).build()

private fun getHistoryDao(appDataBase: AppDatabase): HistoryDao {
    return appDataBase.historyDao()

}