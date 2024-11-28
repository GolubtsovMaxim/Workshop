package com.example.teamcity.ui.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement

class ProjectPage : BasePage() {
    companion object {
        private const val PROJECT_URL = "/project/%s"

        fun open(projectId: String): ProjectPage {
            return Selenide.open(PROJECT_URL.format(projectId), ProjectPage::class.java)
        }
    }

    val title: SelenideElement = `$`("span[class*='ProjectPageHeader']")
}