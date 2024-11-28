package com.example.teamcity.api.models

data class TestData(val project: Project? = null,
                    val user: User? = null,
                    val buildType: BuildType? = null)