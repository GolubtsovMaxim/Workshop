package com.example.teamcity.ui.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.SelenideElement
import com.example.teamcity.ui.elements.ProjectElement

class ProjectsPage : BasePage() {
    companion object {
        private val PROJECTS_URL = "/favorite/projects"
        private var projectsElements : ElementsCollection = `$$`("div[class*='Subproject__container']")

        fun open() : ProjectsPage {
            return Selenide.open(PROJECTS_URL, ProjectsPage::class.java)
        }
    }

    //val projectElements: ElementsCollection = `$$`("div[class*='Subproject__contaioner']")
    private val header: SelenideElement = `$`(".MainPanel__router--gF > div")

    init {
        header.shouldBe(Condition.visible, BASE_WAITING)
    }

    val spanFavoriteProjects: SelenideElement = `$`("span[class='ProjectPageHeader__title--ih']")

    fun getProjects() : List<ProjectElement> {
        return generatePageElements(projectsElements) {element -> ProjectElement(element)}
    }
}