package com.example.quizappkt2.kolg.data.local

import com.example.quizappkt2.kolg.domain.model.History

class LocalRepository(private val dao : HistoryDao) {

    suspend fun getAllHistory(): List<History> {
        return dao.getAllHistory()
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    suspend fun insert(history: History) {
        dao.insert(history)
    }

    suspend fun delete(history: History) {
        dao.delete(history)
    }

}