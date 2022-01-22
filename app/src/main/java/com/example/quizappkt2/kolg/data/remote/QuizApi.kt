package com.example.quizappkt2.kolg.data.remote

import com.example.quizappkt2.kolg.data.remote.models.QuestionsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {

    @GET("/api.php")
    suspend fun getQuestions(@Query("amount") amount: String):
            Response<QuestionsDto>

    @GET("/api.php")
    suspend fun getQuestionsCategory(
        @Query("amount") amount: String,
        @Query("category") category: String
    ): Response<QuestionsDto>

    @GET("/api.php")
    suspend fun getQuestionsDifficulty(
        @Query("amount") amount: String,
        @Query("difficulty") difficulty: String
    ): Response<QuestionsDto>

    @GET("/api.php")
    suspend fun getAllQuestions(
        @Query("amount") amount: String,
        @Query("category") category: String,
        @Query("difficulty") difficulty: String
    ): Response<QuestionsDto>
}