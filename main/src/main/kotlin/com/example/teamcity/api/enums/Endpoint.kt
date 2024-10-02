package com.example.teamcity.api.enums

import com.example.teamcity.api.models.BaseModel
import com.example.teamcity.api.models.BuildType
import com.example.teamcity.api.models.Project
import com.example.teamcity.api.models.User

enum class Endpoint(val url: String, val modelClass: Class<out BaseModel>) {
    BUILD_TYPES("/app/rest/buildTypes", BuildType::class.java),
    PROJECT("/app/rest/projects", Project::class.java),
    USERS("/app/rest/users", User::class.java)
    //PERMISSIONS("/app/rest/server/authSettings", )
}