package com.example.teamcity.api.models

data class Project(val id: String,
                   val name: String,
                   val locator: String = "_Root") : BaseModel() {

}