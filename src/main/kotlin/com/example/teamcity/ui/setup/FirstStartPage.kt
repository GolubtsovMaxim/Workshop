package com.example.teamcity.ui.setup

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.example.teamcity.ui.pages.BasePage

class FirstStartPage : BasePage() {
    private val restoreButton: SelenideElement = Selenide.`$`("#restoreButton")
    private val proceedButton: SelenideElement = Selenide.`$`("#proceedButton")
    private val dbTypeSelect: SelenideElement = Selenide.`$`("#dbType")
    private val acceptLicenseCheckbox: SelenideElement = Selenide.`$`("#accept")
    private val submitButton: SelenideElement = Selenide.`$`("input[type='submit']")

    init {
        restoreButton.shouldBe(Condition.visible, LONG_WAITING)
    }

    companion object {
        fun open(): FirstStartPage {
            return Selenide.open("/", FirstStartPage::class.java)
        }
    }

    fun setupFirstStart(): FirstStartPage {
        proceedButton.click()
        dbTypeSelect.shouldBe(Condition.visible, LONG_WAITING)
        proceedButton.click()
        acceptLicenseCheckbox.should(Condition.exist, LONG_WAITING).scrollTo().click()
        submitButton.click()
        return this
    }
}