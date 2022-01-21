package com.example.quizappkt2.kolg.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quizappkt2.kolg.domain.model.History


@Database(entities = [History::class], version = 1)


    abstract class AppDatabase : RoomDatabase() {
        abstract fun historyDao() : HistoryDao

    }