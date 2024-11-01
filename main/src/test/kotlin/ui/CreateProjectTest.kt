package ui

import com.codeborne.selenide.Condition
import com.example.teamcity.api.enums.Endpoint
import com.example.teamcity.api.models.Project
import com.example.teamcity.ui.pages.ProjectPage
import com.example.teamcity.ui.pages.admin.CreateProjectPage
import io.qameta.allure.Allure.step
import org.testng.annotations.Test

class CreateProjectTest : BaseUiTest() {

    private val REPO_URL : String = "https://github.com/AlexPshe/spring-core-for-qa"

    @Test(description = "User should be able to create a project", groups = ["Regression"])
    fun userCreatesProject() {

        loginAs(testData.user!!)

        CreateProjectPage.open("_Root")
            .createForm(REPO_URL)
            .setupProject(testData.project!!.name, testData.buildType!!.name!!)


        var createdProject = superUserCheckRequests.getRequest(Endpoint.PROJECT)?.read("name:${testData.project!!.name}") as Project?
        softy.assertNotNull(createdProject)

        ProjectPage.open(createdProject?.id!!)
            .title.shouldHave(Condition.exactText(testData.project!!.name))

        step("check that project is visible on Projects page(`http://localhost:8111/favorite/projects`)")
    }

    @Test(description = "User should not be able to create a project", groups = ["Regression"])
    fun userCreatesProjectWithoutName() {
        step("Login as user")
        step("Check number of projects")

        step("Open `Create Project Page` (http://localhost:8111/admin/createObjectMenu.html)")
        step("Send all project parameters (repository URL)")

        step("Click `Proceed`")
        step("Set empty Project Name")
        step("Click `Proceed`")

        // проверка состояния API
        // (корректность отправки данных с UI на API)
        step("Check that number of projects did not change")

        // проверка состояния UI
        // (корректность считывания данных и отображение данных на UI)
        step("Check that error appears `Project name must not be empty`")
    }
}