package com.bmnmrls.domain

sealed class Failure {

    data class GenericError(val exception: Exception) : Failure()

    data class CustomError(val message: String) : Failure()

    object NetworkConnection : Failure()
}
