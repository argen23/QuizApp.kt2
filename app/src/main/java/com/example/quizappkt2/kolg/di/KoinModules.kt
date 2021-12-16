package com.example.quizappkt2.kolg.di

import com.example.quizappkt2.kolg.remote.networkModule
import okhttp3.internal.immutableListOf

val koinModules = immutableListOf(
    repoModule,
    ViewModelModules,
    networkModule,
    useCaseModule
)