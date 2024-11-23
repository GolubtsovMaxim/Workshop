package com.example.teamcity.ui.pages.admin

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement

class CreateBuildPage : CreateBasePage() {
    private val submitAnyWayButton : SelenideElement = `$`(Selectors.byAttribute("value", "Create Duplicate VCS Root"))
    companion object {
        protected const val BUILD_SHOW_MODE = "createBuildTypeMenu"

        fun open (projectID: String) : CreateBuildPage {
            return Selenide.open(CreateBuildPage().CREATE_URL.format
                (projectID, BUILD_SHOW_MODE),
                CreateBuildPage::class.java)
        }
    }

    fun createForm(url: String) : CreateBuildPage {
        baseCreateForm(url)
        return this
    }

    fun setupBuild (buildTypeName : String) : CreateBuildPage {
        buildTypeNameInput.`val`(buildTypeName)
        submitButton.click()
        return this
    }

    fun handleDuplicateVCSRoot () {
        submitAnyWayButton.should(Condition.appear, BASE_WAITING)
        submitAnyWayButton.click()
    }
}