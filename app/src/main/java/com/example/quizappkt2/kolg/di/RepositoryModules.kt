package com.example.quizappkt2.kolg.di

import com.example.quizappkt2.kolg.data.local.LocalRepository
import com.example.quizappkt2.kolg.data.remote.RemoteRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    single { RemoteRepositoryImpl(get(),questionsMapper = get()) }
    single { LocalRepository(get()) }

}