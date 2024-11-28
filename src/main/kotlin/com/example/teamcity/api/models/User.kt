package com.example.teamcity.api.models

import com.example.teamcity.api.annotations.Random
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class User(
    val id: String = "",
    @Random
    val username: String = "",
    @Random
    val password: String?= "",
    val roles: Roles = Roles(emptyList())
) : BaseModel()