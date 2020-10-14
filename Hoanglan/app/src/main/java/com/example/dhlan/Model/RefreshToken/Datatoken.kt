package com.example.dhlan.Model.RefreshToken

import com.google.gson.annotations.SerializedName

class Datatoken  {
    @SerializedName("accessToken")
    var accessToken: String? = null
    @SerializedName("refreshToken")
    var refreshToken: String? = null
    @SerializedName("tokenType")
    var tokenType: String? = null
    @SerializedName("userInfo")
    var userInfo: String? = null

}