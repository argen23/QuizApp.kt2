package com.example.quizappkt2.kolg.domain.use_case

import androidx.lifecycle.LiveData
import com.example.quizappkt2.kolg.domain.RemoteRepository
import com.example.quizappkt2.kolg.domain.model.Questions
import com.example.quizappkt2.kolg.network.Resource
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor (private val repository: RemoteRepository)  {

    fun getQuestions(amount: String,category: String, difficulty: String): LiveData<Resource<Questions>> {
        return repository.getQuestions(amount, category, difficulty)
    }
}