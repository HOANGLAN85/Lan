package com.example.dhlan.Model.Comment

import android.provider.ContactsContract
import com.example.dhlan.Model.Comment.HomeViewModel
import com.example.dhlan.Model.Login.Logindata
import com.google.gson.annotations.SerializedName

class Homedata {
    @SerializedName("data")
    var data: ArrayList<HomeViewModel>? = null
}