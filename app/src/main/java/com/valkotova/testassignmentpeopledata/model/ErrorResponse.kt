package com.valkotova.testassignmentpeopledata.model

@kotlinx.serialization.Serializable
data class ErrorResponse(
    val error: String
)