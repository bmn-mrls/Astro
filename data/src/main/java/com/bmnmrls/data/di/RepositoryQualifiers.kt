package com.bmnmrls.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApodRemoteDataRepository

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApodLocalDataRepository
