package com.valkotova.testassignmentpeopledata.data

import com.valkotova.testassignmentpeopledata.model.Dob

@kotlinx.serialization.Serializable
data class UserData(
    val gender : UserGender,
    val name: UserName,
    val location : Location,
    val picture : UserPicture,
    val dob: Dob,
    val email : String?,
    val phone : String?,
    val cell : String?
)


@kotlinx.serialization.Serializable
data class Location(
    val street : LocationStreet?,
    val city : String?,
    val state : String?,
    val country : String?,
    val postCode : String?
)

@kotlinx.serialization.Serializable
data class UserPicture(
    val large : String?,
    val medium : String?,
    val thumbnail : String?
)

@kotlinx.serialization.Serializable
data class LocationStreet(
    val number : Int,
    val name : String
)

@kotlinx.serialization.Serializable
data class UserName(
    val title: String,
    val first: String,
    val last: String
)

enum class UserGender{
    male, female
}