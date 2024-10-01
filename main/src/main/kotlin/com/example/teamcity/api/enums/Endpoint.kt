package com.example.teamcity.api.enums

import com.example.teamcity.api.models.BuildType

enum class Endpoint(val url: String, val modelClass: Class<BuildType>) {
    BUILD_TYPES("/app/rest/buildTypes", BuildType::class.java)
}