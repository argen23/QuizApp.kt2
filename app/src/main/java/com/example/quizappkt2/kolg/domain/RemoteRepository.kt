package com.example.quizappkt2.kolg.domain

import androidx.lifecycle.LiveData
import com.example.quizappkt2.kolg.domain.model.Questions
import com.example.quizappkt2.kolg.network.Resource



interface RemoteRepository {

    fun getQuestions( amount: String,category: String, difficulty: String) : LiveData<Resource<Questions>>

}