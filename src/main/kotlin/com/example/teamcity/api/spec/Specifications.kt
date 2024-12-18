import com.example.teamcity.api.models.User
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification

class Specifications private constructor() {
    companion object{
        private val spec : Specifications by lazy { Specifications() }
        fun retriveSpec() : Specifications {
            return spec
        }
    }

    private fun reqBuilder(): RequestSpecBuilder {
        return RequestSpecBuilder().apply {
            addFilter(RequestLoggingFilter())
            addFilter(ResponseLoggingFilter())
            setContentType(ContentType.JSON)
            setAccept(ContentType.JSON)
        }
    }

    fun authSpec(user : User?) : RequestSpecification {
        println("http://${user?.username}:${user?.password}@${Config.getProperty("host")}")
        return reqBuilder()
                .setBaseUri("http://${user?.username}:${user?.password}@${Config.getProperty("host")}")
                .build()
    }

    fun superUserAuth() : RequestSpecification {
        println("http://:${Config.getProperty("superUserToken")}@${Config.getProperty("host")}/httpAuth")
        return reqBuilder()
                .setBaseUri("http://:${Config.getProperty("superUserToken")}@${Config.getProperty("host")}/httpAuth")
                .build()
    }

    fun unauthSpec(user : User) : RequestSpecification {
        return reqBuilder().build()
    }
}