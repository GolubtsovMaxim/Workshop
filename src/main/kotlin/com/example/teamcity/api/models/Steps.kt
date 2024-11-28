package com.example.teamcity.api.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Steps(
    val count: Int = 0,
    val step: List<Step> = emptyList()
) : BaseModel() {
}