package com.example.teamcity.api.models

data class BuildType(val id :String,
                     val name: String,
                     val project: Project,
                     val steps: Steps) : BaseModel() {
}