package com.example.quizappkt2.kolg.di

import android.content.Context
import androidx.room.Room
import com.example.quizappkt2.BuildConfig
import com.example.quizappkt2.kolg.data.local.AppDatabase
import com.example.quizappkt2.kolg.data.remote.QuizApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FrameworkModule {

    @Provides
    @Singleton
     fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
     fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
     fun provideApi(retrofit: Retrofit): QuizApi = retrofit.create(QuizApi::class.java)

    @Provides
    @Singleton
    fun getAppDatabase( @ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "Quiz Database"
        ).build()

}