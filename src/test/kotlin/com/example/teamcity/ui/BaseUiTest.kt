package com.example.teamcity.ui

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.example.teamcity.api.BaseTest
import com.example.teamcity.api.enums.Endpoint
import com.example.teamcity.api.models.User
import com.example.teamcity.ui.pages.LoginPage
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeSuite

open class BaseUiTest : BaseTest() {

    protected val REPO_URL : String = "https://github.com/AlexPshe/spring-core-for-qa"

    @BeforeSuite(alwaysRun = true)
    fun setupUiTest() {
        Configuration.browser = Config.getProperty("browser")
        Configuration.baseUrl = "http://" + Config.getProperty("host")
        Configuration.remote = Config.getProperty("remote")
        Configuration.browserSize = Config.getProperty("browserSize")
        Configuration.browserCapabilities.setCapability("selenoid:options",
            mapOf<String, Boolean>("enableVNC" to true, "enableLog" to true))
        //
    }

    @AfterMethod(alwaysRun = true)
    fun closeWebDriver() {
        Selenide.closeWebDriver()
    }

    protected fun loginAs(user: User) {
        superUserCheckRequests.getRequest(Endpoint.USERS)?.create(user)
        LoginPage.open().login(user)
    }
}