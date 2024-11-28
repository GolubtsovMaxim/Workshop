package com.example.teamcity.ui.elements

import com.codeborne.selenide.SelenideElement

open class ProjectElement(element: SelenideElement) : BasePageElement(element) {
    val name: SelenideElement = element.find("span[class*='MiddleEllipsis']")
    val link: SelenideElement = element.find("a")
    val button: SelenideElement = element.find("button")
}