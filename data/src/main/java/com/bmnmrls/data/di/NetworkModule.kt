package com.bmnmrls.data.di

import com.bmnmrls.data.BuildConfig
import com.bmnmrls.data.remote.mappers.ApodRemoteMapper
import com.bmnmrls.data.remote.models.ApodResponse
import com.bmnmrls.data.remote.services.ApodServices
import com.bmnmrls.data.repositories.ApodRemoteRepository
import com.bmnmrls.domain.mappers.Transformer
import com.bmnmrls.domain.models.Apod
import com.bmnmrls.domain.remote.ServiceFactory
import com.bmnmrls.domain.repositories.ApodRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder().apply {
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor { message ->
                        Timber.i(message)
                    }.apply { level = HttpLoggingInterceptor.Level.BODY }
                )
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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    @Singleton
    @Provides
    fun provideApodServices(serviceFactory: ServiceFactory): ApodServices =
        serviceFactory.createApiService(ApodServices::class.java)

    @Singleton
    @ApodRemoteTransformer
    @Provides
    fun provideApodRemoteMapper(): Transformer<ApodResponse, Apod> = ApodRemoteMapper()

    @Singleton
    @ApodRemoteDataRepository
    @Provides
    fun provideApodRemoteRepository(
        apodServices: ApodServices,
        @ApodRemoteTransformer apodRemoteMapper: Transformer<ApodResponse, Apod>
    ): ApodRepository = ApodRemoteRepository(apodServices, apodRemoteMapper)

    companion object {
        private const val READ_TIMEOUT: Long = 10
        private const val CONNECT_TIMEOUT: Long = 5
        private const val API_KEY = "api_key"
    }
}
