package com.example.quizappkt2.kolg.domain

import com.example.quizappkt2.kolg.domain.model.Questions
import com.example.quizappkt2.kolg.domain.model.Result
import com.example.quizappkt2.kolg.data.remote.models.QuestionsDto
import com.example.quizappkt2.kolg.data.remote.models.ResultDto
import javax.inject.Inject


class QuestionsMapper @Inject constructor() {

    fun mapDtoToDomain(questionsDto: QuestionsDto?): Questions {
        return Questions(questionsDto?.response_code, mapListResultDto(questionsDto?.results))
    }

    private fun mapResultDto(resultDto: ResultDto) = Result(
        resultDto.category,
        resultDto.correct_answer,
        resultDto.difficulty,
        resultDto.incorrect_answers,
        resultDto.question,
        resultDto.type,
    )

    private fun mapListResultDto(list: List<ResultDto>?) = list?.map {
        mapResultDto(it)
    }

}