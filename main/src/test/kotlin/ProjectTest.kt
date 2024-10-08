import com.example.teamcity.api.BaseApiTest
import com.example.teamcity.api.enums.Endpoint
import com.example.teamcity.api.models.Project
import com.example.teamcity.api.requests.checked.CheckedRequests
import com.example.teamcity.api.requests.unchecked.UncheckedBase
import org.apache.http.HttpStatus
import org.testng.annotations.Test

@Test(groups = ["Regression"])
class ProjectTest : BaseApiTest() {

    @Test(description = "Super user should be able to create a project", groups = ["Positive", "CRUD"])
    fun superUserCreatesProjectTest() {

        UncheckedBase(Specifications.retriveSpec().superUserAuth(), Endpoint.PROJECT)
            .read(getDefaultProjectCreatedBySuperUser()?.id)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
    }

    @Test(description = "User should be able to create a project", groups = ["Positive", "CRUD"])
    fun userCreatesProjectTest() {

        superUserCheckRequests.getRequest(Endpoint.USERS)?.create(testData.user)
        val userCheckRequests = CheckedRequests(Specifications.retriveSpec().authSpec(testData.user))
        val projectCreatedByUser = (userCheckRequests.getRequest(Endpoint.PROJECT)?.create(testData.project)) as Project

        UncheckedBase(Specifications.retriveSpec().superUserAuth(), Endpoint.PROJECT)
            .read(projectCreatedByUser.id)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
    }

    @Test(description = "Super user should be able to read a project by ID", groups = ["Positive", "CRUD"])
    fun superUserReadsProjectTest() {
        val userCheckRequests = CheckedRequests(Specifications.retriveSpec().superUserAuth())
        (userCheckRequests.getRequest(Endpoint.PROJECT)?.create(testData.project)) as Project

        softy.assertEquals(testData.project?.id,
                            (userCheckRequests.getRequest(Endpoint.PROJECT)?.read(testData.project?.id) as Project?)?.id,
                                "Could not read project by id: ${testData.project?.id}")
    }

    @Test(description = "Super user should be able to delete a project by ID", groups = ["Positive", "CRUD"])
    fun superUserDeletesCreatedProject() {

        UncheckedBase(Specifications.retriveSpec().superUserAuth(), Endpoint.PROJECT)
            .delete(getDefaultProjectCreatedBySuperUser()?.id)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_NO_CONTENT)
    }

    @Test(description = "Super user should not be able to create a project with existing ID", groups = ["Negative", "CRUD"])
    fun superUserCreatesProjectWithExistingID() {

        UncheckedBase(Specifications.retriveSpec().superUserAuth(), Endpoint.PROJECT)
            .create(getDefaultProjectCreatedBySuperUser())
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
    }

    @Test(description = "User should not be able to create a project with existing ID", groups = ["Negative", "CRUD"])
    fun userCreatesProjectWithExistingID() {

        superUserCheckRequests.getRequest(Endpoint.USERS)?.create(testData.user)
        val userCheckRequests = CheckedRequests(Specifications.retriveSpec().authSpec(testData.user))
        val createdProject = (userCheckRequests.getRequest(Endpoint.PROJECT)?.create(testData.project)) as Project

        UncheckedBase(Specifications.retriveSpec().superUserAuth(), Endpoint.PROJECT)
            .create(createdProject)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
    }

    @Test()
    fun superUserCreatesProjectWithInvalidNameTest() {

        val createdProject = getDefaultProjectCreatedBySuperUser()
        testData.project?.name = ""

        UncheckedBase(Specifications.retriveSpec().superUserAuth(), Endpoint.PROJECT)
            .create(createdProject)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
    }

    @Test(description = "Super user should not be able to read a project with non existing ID", groups = ["Negative", "CRUD"])
    fun superUserReadsProjectWithNonExistingID() {
        // по-хорошему нужно получить set всех созданных проектов, сгенерировать ID, проверить что такого ID нет и только потом читать

        UncheckedBase(Specifications.retriveSpec().superUserAuth(), Endpoint.PROJECT)
            .read("")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_NOT_FOUND)
    }

    @Test(description = "Super user should not be able to delete a project with non existing ID", groups = ["Negative", "CRUD"])
    fun superUserDeletesProjectWithNonExistingNonExistingID() {
        // по-хорошему нужно получить set всех созданных проектов, сгенерировать ID, проверить что такого ID нет и только потом удалять #2

        UncheckedBase(Specifications.retriveSpec().superUserAuth(), Endpoint.PROJECT)
            .delete("")
            .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
    }

    private fun getDefaultProjectCreatedBySuperUser() : Project? {
        val userCheckRequests = CheckedRequests(Specifications.retriveSpec().superUserAuth())
        (userCheckRequests.getRequest(Endpoint.PROJECT)?.create(testData.project)) as Project

        return testData.project
    }
}