package com.example.teamcity.api.models

data class Steps(val count: Int, val stepList: List<Step>) : BaseModel() {
}