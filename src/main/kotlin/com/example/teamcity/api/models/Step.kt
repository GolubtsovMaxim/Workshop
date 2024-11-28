package com.example.teamcity.api.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Step(val id: String = "",
                val name: String = "",
                val type: String = "simpleRunner") : BaseModel() {
}