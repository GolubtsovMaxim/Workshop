package ui

import com.example.teamcity.api.enums.Endpoint
import com.example.teamcity.ui.pages.LoginPage
import io.qameta.allure.Allure.step
import org.testng.annotations.Test

class CreateProjectTest : BaseUiTest() {

    @Test(description = "User should be able to create a project", groups = ["Regression"])
    fun userCreatesProject() {
        step("Login as user")
        superUserCheckRequests.getRequest(Endpoint.USERS)?.create(testData.user)
        LoginPage.open().login(testData.user!!)
        step("Open  `create project page` (http://localhost:8111/admin/createObjectMenu.html)")
        step("Send all project parameters")
        //step("`https://github.com/AlexPshe/spring-core-for-qa`")
        step("Proceed")
        step("Fix project name and build type name values")
        step("Proceed")
        step("Check all entities are succesfully created with correct data on API level")
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