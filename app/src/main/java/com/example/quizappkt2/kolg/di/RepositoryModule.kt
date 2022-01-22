package com.example.quizappkt2.kolg.di

import com.example.quizappkt2.kolg.data.remote.RemoteRepositoryImpl
import com.example.quizappkt2.kolg.domain.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun remoteRepositoryImpl(remoteRepository: RemoteRepositoryImpl) : RemoteRepository

}