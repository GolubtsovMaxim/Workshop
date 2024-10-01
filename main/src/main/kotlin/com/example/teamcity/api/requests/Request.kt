package com.example.teamcity.api.requests

import com.example.teamcity.api.enums.Endpoint
import io.restassured.specification.RequestSpecification

open class Request(val spec: RequestSpecification, val endpoint: Endpoint) {
}