package com.example.teamcity.api.requests.checked

import com.example.teamcity.api.enums.Endpoint
import com.example.teamcity.api.models.BaseModel
import com.example.teamcity.api.requests.Request
import com.example.teamcity.api.requests.unchecked.UncheckedBase
import io.restassured.specification.RequestSpecification
import org.apache.http.HttpStatus

class CheckedBase<T: BaseModel>(spec: RequestSpecification, endpoint: Endpoint) :
    Request(spec, endpoint), CrudInterface {

        val uncheckedBase : UncheckedBase = UncheckedBase(spec, endpoint)

    override fun create(model: BaseModel): T {
        return uncheckedBase
            .create(model)
            .then().assertThat().statusCode(HttpStatus.SC_OK)
            .extract().`as`(endpoint.modelClass) as T
    }

    override fun read(id: String?): Any {
        return uncheckedBase
            .read(id)
            .then().assertThat().statusCode(HttpStatus.SC_OK)
            .extract().`as`(endpoint.modelClass) as T
    }

    override fun update(id: String, model: BaseModel): T {
        return uncheckedBase
                .update(id, model)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().`as`(endpoint.modelClass) as T
    }

    override fun delete(id: String): Any {
        return uncheckedBase
            .delete(id)
            .then().assertThat().statusCode(HttpStatus.SC_OK)
            .extract().asString()
    }
}