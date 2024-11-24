package com.example.teamcity.api.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
open class ServerAuthSettings(var perProjectPermissions: Boolean,
    var modules: AuthModules,
    ) : BaseModel() {
}