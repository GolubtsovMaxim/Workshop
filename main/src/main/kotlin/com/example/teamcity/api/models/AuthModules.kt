package com.example.teamcity.api.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class AuthModules(val module: List<AuthModule> = listOf<AuthModule>()) : BaseModel() {
}