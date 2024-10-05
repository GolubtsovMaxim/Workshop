import com.example.teamcity.api.BaseApiTest
import com.example.teamcity.api.enums.Endpoint
import com.example.teamcity.api.generators.TestDataGenerator.generate
import com.example.teamcity.api.models.BuildType
import com.example.teamcity.api.models.Project
import com.example.teamcity.api.requests.checked.CheckedRequests
import com.example.teamcity.api.requests.unchecked.UncheckedBase
import io.qameta.allure.Allure.step
import org.apache.http.HttpStatus
import org.hamcrest.Matchers
import org.testng.annotations.Test

@Test(groups = ["Regression"])
class BuildTypeTest : BaseApiTest() {

    @Test(description = "User should be able to create build type", groups = ["Positive", "CRUD"])
    fun userCreatesBuildTypeTest() {
        //var testData = generate()
        superUserCheckRequests.getRequest(Endpoint.USERS)?.create(testData.user)

        val userCheckRequests = CheckedRequests(Specifications.retriveSpec().authSpec(testData.user))

        (userCheckRequests.getRequest(Endpoint.PROJECT)?.create(testData.project)) as Project

        userCheckRequests.getRequest(Endpoint.BUILD_TYPES)?.create(testData.buildType)

        val createdBuildType = (userCheckRequests.getRequest(Endpoint.BUILD_TYPES)?.read(testData.buildType?.id)) as BuildType
        softy.assertEquals(testData.buildType?.name, (createdBuildType).name, "Build type name is not correct")
    }

    @Test(description = "User should not be able to create two build types with the same id", groups = ["Negative", "CRUD"])
    fun userCreatesTwoBuildTypesWithTheSameIdTest() {
        //var testData = generate()
        superUserCheckRequests.getRequest(Endpoint.USERS)?.create(testData.user)

        val userCheckRequests = CheckedRequests(Specifications.retriveSpec().authSpec(testData.user))
        (userCheckRequests.getRequest(Endpoint.PROJECT)?.create(testData.project)) as Project

        val buildTypeWithSameID = generate(listOf<Project>(testData.project!!), BuildType::class.java, testData.buildType?.id)
        userCheckRequests.getRequest(Endpoint.BUILD_TYPES)?.create(testData.buildType)

        UncheckedBase(Specifications.retriveSpec().authSpec(testData.user), Endpoint.BUILD_TYPES)
            .create(buildTypeWithSameID)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .body(Matchers.containsString("The build configuration / template ID \"${testData.buildType?.id}\" is already used by another configuration or template"))
    }

    @Test(description = "Project admin should be able to create build type for their project", groups = ["Positive", "Roles"])
    fun projectAdminCreatesBuildTypeTest() {
        step("Create user")
        step("Create project")
        step("Grant user PROJECT_ADMIN role in project")

        step("Create buildType for project by user (PROJECT_ADMIN)")
        step("Check buildType was created successfully")
    }

    @Test(description = "Project admin should not be able to create build type for not their project", groups = ["Negative", "Roles"])
    fun projectAdminCreatesBuildTypeForAnotherUserProjectTest() {
        step("Create user1")
        step("Create project1")
        step("Grant user1 PROJECT_ADMIN role in project1")

        step("Create user2")
        step("Create project2")
        step("Grant user2 PROJECT_ADMIN role in project2")

        step("Create buildType for project1 by user2")
        step("Check buildType was not created with forbidden code")
    }
}