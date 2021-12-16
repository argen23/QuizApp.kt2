package com.example.quizappkt2.kolg.domain.use_case

import androidx.lifecycle.LiveData
import com.example.quizappkt2.kolg.domain.model.Questions
import com.example.quizappkt2.kolg.network.Resource
import com.example.quizappkt2.kolg.remote.RemoteRepositoryImpl

class GetQuestionsUseCase(private val repository: RemoteRepositoryImpl) {

    fun getQuestions(amount: String,category: String, difficulty: String): LiveData<Resource<Questions>> {
        return repository.getQuestions(amount, category, difficulty)
    }
}