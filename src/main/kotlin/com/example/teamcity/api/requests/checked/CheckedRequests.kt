package com.example.teamcity.api.requests.checked

import com.example.teamcity.api.enums.Endpoint
import com.example.teamcity.api.models.BaseModel
import io.restassured.specification.RequestSpecification
import java.util.EnumMap
import kotlin.collections.set

class CheckedRequests constructor(spec: RequestSpecification) {
    private val requests = EnumMap<Endpoint, CheckedBase<out BaseModel>>(Endpoint::class.java)

    init {
        Endpoint.entries.forEach { endpoint ->
            requests[endpoint] = CheckedBase(spec, endpoint)
        }
    }

    fun getRequest(endpoint: Endpoint) : CheckedBase<out BaseModel>? {
        return requests[endpoint] as CheckedBase<BaseModel>?
    }
}