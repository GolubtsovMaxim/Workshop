package com.example.teamcity.api.requests

import com.example.teamcity.api.models.ServerAuthSettings
import io.restassured.RestAssured
import io.restassured.specification.RequestSpecification
import org.apache.http.HttpStatus

class ServerAuthRequest(specification: RequestSpecification) {
    private val SERVER_AUTH_SETTINGS_URL = "/app/rest/server/authSettings"
    private val spec: RequestSpecification? = specification

    fun read() : ServerAuthSettings {

        println(RestAssured.given().spec(spec).get(SERVER_AUTH_SETTINGS_URL).body.asString())

        return RestAssured.given()
                .spec(spec)
                .get(SERVER_AUTH_SETTINGS_URL)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().`as`(ServerAuthSettings::class.java)
    }

    fun update(authSettings: ServerAuthSettings) : ServerAuthSettings {
        return RestAssured.given()
            .spec(spec)
            .body(authSettings)
            .put(SERVER_AUTH_SETTINGS_URL)
            .then().assertThat().statusCode(HttpStatus.SC_OK)
            .extract().`as`(ServerAuthSettings::class.java)
    }
}