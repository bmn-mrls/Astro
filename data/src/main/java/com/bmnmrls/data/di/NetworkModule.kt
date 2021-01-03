package com.bmnmrls.data.di

import com.bmnmrls.data.BuildConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create(GsonBuilder().create())

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            Timber.i(message)
        }.apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient().newBuilder().apply {
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                addInterceptor(httpLoggingInterceptor)
            }
            addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader(API_KEY, BuildConfig.API_KEY)
                    .build()
                return@addInterceptor chain.proceed(request)
            }
        }.build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .build()

    companion object {
        private const val READ_TIMEOUT: Long = 10
        private const val CONNECT_TIMEOUT: Long = 5
        private const val API_KEY = "api_key"
    }

}