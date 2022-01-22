package com.example.quizappkt2.kolg.presentation.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizappkt2.kolg.domain.use_case.GetQuestionsUseCase
import com.example.quizappkt2.kolg.domain.model.Questions
import com.example.quizappkt2.kolg.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel  @Inject constructor (private val getQuestionsUseCase: GetQuestionsUseCase) : ViewModel() {

    val progressBar = MutableLiveData<Boolean>()

    fun getQuestions(
        amount: String,
        category: String,
        difficulty: String
    ) : LiveData<Resource<Questions>>{
        return getQuestionsUseCase.getQuestions(amount, category, difficulty)
    }

}