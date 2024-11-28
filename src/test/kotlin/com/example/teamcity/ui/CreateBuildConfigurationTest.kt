package com.example.teamcity.ui

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$$`
import com.example.teamcity.api.enums.Endpoint
import com.example.teamcity.api.models.BuildType
import com.example.teamcity.api.models.Project
import com.example.teamcity.ui.pages.ProjectPage
import com.example.teamcity.ui.pages.ProjectsPage
import com.example.teamcity.ui.pages.admin.CreateBuildPage
import com.example.teamcity.ui.pages.admin.CreateProjectPage
import org.testng.annotations.Test

class CreateBuildConfigurationTest : BaseUiTest() {

    @Test(description = "User creates build configuration for existing project", groups = ["Regression, Positive"])
    fun userCreatesBuildConfiguration() {
        loginAs(testData.user!!)

        CreateProjectPage.open("_Root")
            .createForm(REPO_URL)
            .setupProject(testData.project!!.name, testData.buildType!!.name!!)

        var createdProject = superUserCheckRequests.getRequest(Endpoint.PROJECT)?.read("name:${testData.project!!.name}") as Project?
        softy.assertNotNull(createdProject)

        val foundProjects = ProjectsPage.open()
            .getProjects()
            .any { it.name.text == testData.project!!.name }

        softy.assertTrue(foundProjects)

        val buildConfigurationName = "NewBuild"

        CreateBuildPage.open(createdProject?.id!!)
            .createForm(REPO_URL)
            .setupBuild(buildConfigurationName)
            .handleDuplicateVCSRoot()

        ProjectPage.open(createdProject.id)

        var element = `$$`(".MiddleEllipsis__searchable--uZ")
            .filterBy(Condition.text(buildConfigurationName))
            .first()

        softy.assertTrue(element.text == buildConfigurationName)

        val createdBuildType = (superUserCheckRequests.getRequest(Endpoint.BUILD_TYPES)?.read(testData.buildType?.name))
        softy.assertEquals((createdBuildType as BuildType).name, testData.buildType?.name, "Build type by this id doesn't exist")
    }

    @Test(description = "User cannot create build configuration with empty name for existing project", groups = ["Regression, Positive"])
    fun userCannotCreateBuildConfigurationForExistingProject() {
        loginAs(testData.user!!)

        CreateProjectPage.open("_Root")
            .createForm(REPO_URL)
            .setupProject(testData.project!!.name, testData.buildType!!.name!!)

        var createdProject = superUserCheckRequests.getRequest(Endpoint.PROJECT)?.read("name:${testData.project!!.name}") as Project?
        softy.assertNotNull(createdProject)

        val foundProjects = ProjectsPage.open()
            .getProjects()
            .any { it.name.text == testData.project!!.name }

        softy.assertTrue(foundProjects)

        val buildConfigurationName = ""

        CreateBuildPage.open(createdProject?.id!!)
            .createForm(REPO_URL)
            .setupBuild(buildConfigurationName)

        ProjectPage.open(createdProject.id)

        var elements = `$$`(".MiddleEllipsis__searchable--uZ")

        softy.assertTrue(elements.isEmpty)
        val createdBuildType = (superUserCheckRequests.getRequest(Endpoint.BUILD_TYPES)?.read(testData.buildType?.name))
        softy.assertNotEquals(buildConfigurationName, createdBuildType, "Build type by this id does exist!")
    }
}