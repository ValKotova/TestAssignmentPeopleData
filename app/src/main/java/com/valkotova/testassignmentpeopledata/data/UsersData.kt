package com.valkotova.testassignmentpeopledata.data

@kotlinx.serialization.Serializable
data class UsersData(
    val results : List<UserData>
)