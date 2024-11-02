package com.example.teamcity.ui.elements

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

abstract class BasePageElement(private var element : SelenideElement) {

    protected fun find(selector: By): SelenideElement{
        return element.`$`(selector)
    }

    protected fun find(cssSelector: String): SelenideElement {
        return element.`$`(cssSelector)
    }

    protected fun findAll(selector: By): ElementsCollection {
        return element.`$$`(selector)
    }

    protected fun findAll(cssSelector: String): ElementsCollection {
        return element.`$$`(cssSelector)
    }
}