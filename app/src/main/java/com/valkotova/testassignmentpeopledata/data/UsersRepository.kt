package com.valkotova.testassignmentpeopledata.data

import kotlinx.serialization.json.Json
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val api : UsersApi,
    private val json : Json
) : BaseBackendApi(json){
    suspend fun getUsers(page : Int) : List<UserData>{
        val res = safeApiCall { api.getUsers(page) }
        return    res.results
    }
}