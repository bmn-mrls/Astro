package com.bmnmrls.domain.repositories

import com.bmnmrls.domain.DataState
import com.bmnmrls.domain.models.Apod

interface ApodRepository {

    suspend fun getApod(date: String): DataState<Apod>

    suspend fun getApodsByDateRange(startDate: String, endDate: String): DataState<List<Apod>>
}
