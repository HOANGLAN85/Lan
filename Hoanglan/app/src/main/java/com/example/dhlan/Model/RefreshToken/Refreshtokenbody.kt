package com.example.dhlan.Model.RefreshToken

import com.google.gson.annotations.SerializedName

class Refreshtokenbody {
    @SerializedName("refreshToken")
    var refreshToken:String? = null
    @SerializedName("deviceToken")
    var deviceToken: String? = null
    @SerializedName("os")
    var os:String? = null
    @SerializedName("versionApp")
    var versionApp:String? = null

    constructor(refreshToken: String?, deviceToken: String?, os: String?, versionApp: String?) {
        this.refreshToken = refreshToken
        this.deviceToken = deviceToken
        this.os = os
        this.versionApp = versionApp
    }
}