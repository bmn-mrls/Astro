package com.bmnmrls.data.remote.services

import com.bmnmrls.data.remote.models.ApodResponse
import com.bmnmrls.data.remote.services.ServicesNames.APOD
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodServices {

    @GET(APOD)
    suspend fun getApod(@Query("date") date: String): Response<ApodResponse>

    @GET(APOD)
    suspend fun getApodsByDateRange(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): Response<List<ApodResponse>>
}
