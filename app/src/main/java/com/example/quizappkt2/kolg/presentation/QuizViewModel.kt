package com.example.quizappkt2.kolg.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel () : ViewModel() {

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

}