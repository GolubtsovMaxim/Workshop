package com.example.teamcity.ui.pages.admin

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.page
import com.codeborne.selenide.SelenideElement
import com.example.teamcity.ui.pages.ProjectsPage

open class CreateProjectPage : CreateBasePage() {

    protected val projectNameInput : SelenideElement = `$`("#projectName")

    companion object {
        protected const val PROJECT_SHOW_MODE = "createProjectMenu"

        fun open (projectID : String) : CreateProjectPage {
            return Selenide.open(CreateProjectPage().CREATE_URL.format
                (projectID, PROJECT_SHOW_MODE),
                CreateProjectPage::class.java)
        }
    }

    fun createForm(url: String) : CreateProjectPage {
        baseCreateForm(url)
        return this
    }

    fun setupProject (projectName : String, buildTypeName : String) : ProjectsPage {
        projectNameInput.`val`(projectName)
        buildTypeNameInput.`val`(buildTypeName)
        submitButton.click()
        return page(ProjectsPage::class.java)
    }
}