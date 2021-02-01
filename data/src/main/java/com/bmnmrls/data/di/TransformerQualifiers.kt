package com.bmnmrls.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApodRemoteTransformer

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApodLocalTransformer
