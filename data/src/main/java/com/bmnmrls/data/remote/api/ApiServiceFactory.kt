package com.bmnmrls.data.remote.api

import com.bmnmrls.domain.remote.ServiceFactory
import retrofit2.Retrofit
import javax.inject.Inject

class ApiServiceFactory @Inject constructor(private val retrofit: Retrofit) : ServiceFactory {

    override fun <T> createApiService(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}
