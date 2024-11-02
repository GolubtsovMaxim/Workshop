package com.example.teamcity.ui.pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.SelenideElement
import com.example.teamcity.ui.elements.BasePageElement
import java.time.Duration

abstract class BasePage {
    protected val BASE_WAITING: Duration = Duration.ofSeconds(30)

    protected fun <T : BasePageElement> generatePageElements(collection: ElementsCollection, creator: (SelenideElement) -> T): List<T> {
        var result = collection.map(creator).toList()
        return result
    }
}