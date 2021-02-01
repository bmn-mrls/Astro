package com.bmnmrls.data.repositories

import com.bmnmrls.data.di.ApodRemoteTransformer
import com.bmnmrls.data.remote.models.ApodResponse
import com.bmnmrls.data.remote.services.ApodServices
import com.bmnmrls.domain.DataState
import com.bmnmrls.domain.Failure
import com.bmnmrls.domain.exceptions.RequestException
import com.bmnmrls.domain.mappers.Transformer
import com.bmnmrls.domain.models.Apod
import com.bmnmrls.domain.repositories.ApodRepository
import javax.inject.Inject

class ApodRemoteRepository @Inject constructor(
    private val apodServices: ApodServices,
    @ApodRemoteTransformer private val apodRemoteMapper: Transformer<ApodResponse, Apod>
) : ApodRepository {

    override suspend fun getApod(date: String): DataState<Apod> {
        return try {
            val response = apodServices.getApod(date)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                DataState.Success(apodRemoteMapper.transform(result))
            } else {
                DataState.Error(Failure.CustomError(response.message()))
            }
        } catch (e: Exception) {
            DataState.Error(Failure.GenericError(RequestException(e.message)))
        }
    }

    override suspend fun getApodsByDateRange(
        startDate: String,
        endDate: String
    ): DataState<List<Apod>> {
        return try {
            val response = apodServices.getApodsByDateRange(startDate, endDate)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                if (result.isNotEmpty()) {
                    DataState.Success(apodRemoteMapper.transformCollection(result))
                } else {
                    DataState.Empty
                }
            } else {
                DataState.Error(Failure.CustomError(response.message()))
            }
        } catch (e: Exception) {
            DataState.Error(Failure.GenericError(RequestException(e.message)))
        }
    }
}
