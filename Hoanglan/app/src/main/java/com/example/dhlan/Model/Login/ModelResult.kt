package com.example.dhlan.Model.Login

import com.google.gson.annotations.SerializedName

class ModelResult {
    @SerializedName("accessToken")
    var accessToken: String? = null
    @SerializedName("refreshToken")
    var refreshToken: String? = null
    @SerializedName("userInfo")
    var userInfo: Logindata? = null

}