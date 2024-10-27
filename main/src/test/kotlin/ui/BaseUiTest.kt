package ui

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.example.teamcity.api.BaseTest
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeSuite

open class BaseUiTest : BaseTest() {

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
}