package com.example.teamcity.api.models

import com.example.teamcity.api.annotations.Parameterizable
import com.example.teamcity.api.annotations.Random
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BuildType(@Random @Parameterizable val id :String? = null,
                     @Random val name: String? = null,
                     var project: Project? = null,
                     val steps: Steps = Steps(0, emptyList())) : BaseModel() {
}