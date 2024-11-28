package com.example.teamcity.ui.pages.admin

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.example.teamcity.ui.pages.BasePage

abstract class CreateBasePage : BasePage() {
    val CREATE_URL: String = "/admin/createObjectMenu.html?projectId=%s&showMode=%s"

    protected val urlInput : SelenideElement = `$`("#url")
    protected val submitButton  : SelenideElement = `$`(Selectors.byAttribute("value", "Proceed"))
    protected val buildTypeNameInput : SelenideElement = `$`("#buildTypeName")
    protected val connectionSuccessfulMessage : SelenideElement = `$`(".connectionSuccessful")

    protected fun baseCreateForm(url: String) {
        urlInput.`val`(url)
        submitButton.click()
        connectionSuccessfulMessage.should(Condition.appear, BASE_WAITING)
    }
}