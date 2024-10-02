package com.example.teamcity.api.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class User @JsonCreator constructor(
    @JsonProperty("username") val username: String,
    @JsonProperty("password") val password: String?
) : BaseModel() {
    // val username: String,
    // val password: String
}