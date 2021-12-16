package com.example.quizappkt2.kolg.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.quizappkt2.kolg.domain.model.Questions
import com.example.quizappkt2.kolg.domain.QuestionsMapper
import com.example.quizappkt2.kolg.domain.RemoteRepository
import com.example.quizappkt2.kolg.remote.models.QuestionsDto
import com.example.quizappkt2.kolg.network.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class RemoteRepositoryImpl(private val quizApi: QuizApi , private val questionsMapper: QuestionsMapper) : RemoteRepository {

    override fun getQuestions(
        amount: String,
        category: String,
        difficulty: String
    ): LiveData<Resource<Questions>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))

        val response: Response<QuestionsDto> = if (category == "All" && difficulty == "All") {
            quizApi.getQuestions(amount)
        } else if (category != "All" && difficulty == "All") {
            quizApi.getQuestionsCategory(amount, category)
        } else if (category == "All" && difficulty != "All") {
            quizApi.getQuestionsDifficulty(amount, difficulty)
        } else {
            quizApi.getAllQuestions(amount, category, difficulty)
        }

        emit(
            if (response.isSuccessful) Resource.success(questionsMapper.mapDtoToDomain(response.body()),response.code())
            else Resource.error(response.message() , questionsMapper.mapDtoToDomain(response.body()))
        )
    }
}

