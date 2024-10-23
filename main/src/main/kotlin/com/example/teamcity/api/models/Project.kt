package com.example.teamcity.api.models

import com.example.teamcity.api.annotations.Random
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Project(@Random val id: String = "",
              @Random var name: String = "",
              var locator: String? = null) : BaseModel()