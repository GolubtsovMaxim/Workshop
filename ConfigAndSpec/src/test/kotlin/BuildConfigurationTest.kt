import io.restassured.RestAssured
import org.apache.http.HttpStatus
import kotlin.test.Test

import teamcity.api.models.User
import teamcity.api.spec.Specifications

class BuildConfigurationTest : BaseApiTest() {

    @Test
    fun buildConfigurationTest() {

        var user = User(username = "Maxim", password = "SuperPassWord")

        val token = RestAssured
            .given()
            .spec(Specifications.retriveSpec().authSpec(user))
            .get("/authenticationTest.html?crsf")
            .then().assertThat().statusCode(HttpStatus.SC_OK)
            .extract().asString()

        println(token)
    }
}