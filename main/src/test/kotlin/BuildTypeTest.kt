import com.example.teamcity.api.BaseApiTest
import com.example.teamcity.api.enums.Endpoint
import com.example.teamcity.api.generators.TestDataGenerator.generate
import com.example.teamcity.api.models.BuildType
import com.example.teamcity.api.models.Project
import com.example.teamcity.api.models.User
import com.example.teamcity.api.requests.checked.CheckedRequests
import io.qameta.allure.Allure.step
import org.testng.annotations.Test

@Test(groups = ["Regression"])
class BuildTypeTest : BaseApiTest() {

    @Test(description = "User should be able to create build type", groups = ["Positive", "CRUD"])
    fun userCreatesBuildTypeTest() {
        //generate user
        val user = generate(User::class.java)

        //create SUPERuser
        superUserCheckRequests.getRequest(Endpoint.USERS)?.create(user)
        //user can send different requests
        val userCheckRequests = CheckedRequests(Specifications.retriveSpec().authSpec(user))

        var projectLocal = generate(Project::class.java)
        //val projectId = AtomicReference<String>("")

        projectLocal = (userCheckRequests.getRequest(Endpoint.PROJECT)?.create(projectLocal)) as Project
        projectLocal.locator = null

        val buildType = generate(listOf<Project>(projectLocal), BuildType::class.java)

        userCheckRequests.getRequest(Endpoint.BUILD_TYPES)?.create(buildType)

        val createdBuildType = (userCheckRequests.getRequest(Endpoint.BUILD_TYPES)?.read(buildType.id)) as BuildType
        softy.assertEquals(buildType.name, (createdBuildType).name, "Build type name is not correct")
    }

    @Test(description = "User should not be able to create two build types with the same id", groups = ["Negative", "CRUD"])
    fun userCreatesTwoBuildTypesWithTheSameIdTest() {
        step("Create user")
        step("Create project by user")
        step("Create buildType1 for project by user")
        step("Create buildType2 with same id as buildType1 for project by user")
        step("Check buildType2 was not created with bad request code")
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