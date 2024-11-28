package com.example.teamcity.api

import com.example.teamcity.api.generators.TestDataGenerator.generate
import com.example.teamcity.api.models.AuthModules
import com.example.teamcity.api.models.ServerAuthSettings
import com.example.teamcity.api.requests.ServerAuthRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite

open class BaseApiTest : BaseTest() {
    private val serverAuthRequest: ServerAuthRequest =
        ServerAuthRequest(Specifications.retriveSpec().superUserAuth())

    private lateinit var authModules: AuthModules
    private var perProjectPermissions: Boolean = false

    @BeforeSuite(alwaysRun = true)
    fun setUpServerAuthSettings() {
        /// ALERT
        // perProjectPermissions = serverAuthRequest.read().perProjectPermissions
        perProjectPermissions = true
        authModules = generate(AuthModules::class.java)

        val serverAuthSettings = ServerAuthSettings(perProjectPermissions = perProjectPermissions,
            modules = authModules)

        println("JOPA")
        println("Request Body: ${ObjectMapper().writeValueAsString(serverAuthSettings)}")

        serverAuthRequest.update(serverAuthSettings)

        @AfterSuite(alwaysRun = true)
        fun cleanUpServerAuthSettings() {
            // Возвращаем настройке perProjectPermissions исходное значение
            serverAuthRequest.update(ServerAuthSettings(
                perProjectPermissions = perProjectPermissions,
                modules = authModules)
            )
        }
    }
}