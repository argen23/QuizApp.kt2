package com.example.quizappkt2.kolg.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizappkt2.kolg.data.local.LocalRepository
import com.example.quizappkt2.kolg.domain.model.History
import kotlinx.coroutines.launch

class QuizViewModel (private val localRepository: LocalRepository) : ViewModel() {

    private var pos = 0
    private var ans = 0

    private val position = MutableLiveData<Int>()
    private val correctAns = MutableLiveData<Int>()

    fun getCorrectAns(): Int {
        return if (correctAns.value != null) correctAns.value!! else 0
    }

    fun getPosition(): Int {
        position.value = pos++
        return position.value!!
    }

    fun setCorrectAns() {
        correctAns.value = ++ans
    }

    fun insert(history: History) {
        viewModelScope.launch {
            localRepository.insert(history)
        }
    }

}