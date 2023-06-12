package com.valkotova.testassignmentpeopledata.data

import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {
    @GET("api?results=100&seed=test&inc=gender,name,location,picture,dob,phone,cell,email")
    suspend fun getUsers(
        @Query("page") page: Int
    ): UsersData
}