package com.example.teamcity.api.requests.unchecked

import com.example.teamcity.api.enums.Endpoint
import io.restassured.specification.RequestSpecification
import java.util.EnumMap

class UncheckedRequests constructor(spec: RequestSpecification) {
    private val requests = EnumMap<Endpoint, UncheckedBase>(Endpoint::class.java)

    init {
        Endpoint.entries.forEach { endpoint ->
            requests[endpoint] = UncheckedBase(spec, endpoint)
        }
    }

    fun getRequest(endpoint: Endpoint) : UncheckedBase? {
        return requests[endpoint]
    }
}