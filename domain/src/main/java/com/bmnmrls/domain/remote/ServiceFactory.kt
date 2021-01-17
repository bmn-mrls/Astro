package com.bmnmrls.domain.remote

interface ServiceFactory {

    fun <T> createApiService(serviceClass: Class<T>): T
}
