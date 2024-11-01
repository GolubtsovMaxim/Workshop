package com.example.teamcity.api.requests.unchecked

import com.example.teamcity.api.enums.Endpoint
import com.example.teamcity.api.models.BaseModel
import com.example.teamcity.api.requests.Request
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification

class UncheckedBase(spec: RequestSpecification, endpoint: Endpoint) :
    Request(spec, endpoint), CrudInterface {
    override fun create(model: BaseModel?): Response {
        return RestAssured
            .given()
            .spec(spec)
            .body(model)
            .post(endpoint.url)
    }

    override fun read(locator: String?): Response {
        return RestAssured
            .given()
            .spec(spec)
            .get(endpoint.url + "/$locator")
    }

    override fun update(locator: String?, model: BaseModel?): Response {
        return RestAssured
            .given()
            .body(model)
            .spec(spec)
            .put(endpoint.url + "/$locator")
    }

    fun updateSpecificUrl(url: String?, model: BaseModel?): Response {
        return RestAssured
            .given()
            .spec(spec)
            .body(model)
            .put(url, model)
    }

    override fun delete(locator: String?): Response {
        return RestAssured
            .given()
            .spec(spec)
            .delete(endpoint.url + "/$locator")
    }

}