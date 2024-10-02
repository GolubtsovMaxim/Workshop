package com.example.teamcity.api.models

import com.example.teamcity.api.annotations.Random
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class User(
    @Random
    val username: String = "",
    @Random
    val password: String?= ""
) : BaseModel() {
}