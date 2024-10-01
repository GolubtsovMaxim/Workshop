package com.example.teamcity.api.models

data class Step(val id: String,
                val name: String,
                val type: String = "simpleRunner") : BaseModel() {
}