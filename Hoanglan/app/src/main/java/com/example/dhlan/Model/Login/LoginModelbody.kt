package com.example.dhlan.Model.Login

import com.google.gson.annotations.SerializedName

class LoginModelbody {
    @SerializedName("username")
    var username: String? = null
    @SerializedName("password")
    var password: String? = null
    @SerializedName("deviceToken")
    var deviceToken: String? = null
    @SerializedName("language")
    var language: String? = null
    @SerializedName("os")
    var os: String? = null
    @SerializedName("versionApp")
    var versionApp: String? = null

    constructor(
        username: String?,
        password: String?,
        deviceToken: String?,
        language: String?,
        os: String?,
        versionApp: String?
    ) {
        this.username = username
        this.password = password
        this.deviceToken = deviceToken
        this.language = language
        this.os = os
        this.versionApp = versionApp
    }
}
