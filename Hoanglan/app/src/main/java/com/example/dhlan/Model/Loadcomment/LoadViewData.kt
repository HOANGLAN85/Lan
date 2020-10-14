package com.example.dhlan.Model.Loadcomment

import com.google.gson.annotations.SerializedName

class LoadViewData {
    @SerializedName("documentId")
    var documentId: Int? =  null
    @SerializedName("staffName")
    var staffName: String? = null
    @SerializedName("position")
    var position:String? = null
    @SerializedName("departmentName")
    var departmentName:String? = null
    @SerializedName("status")
    var status:String? = null
    @SerializedName("statusDate")
    var statusDate:String? = null
    @SerializedName("ozdFileName")
    var ozdFileName: String? = null
    @SerializedName("dueDate")
    var dueDate: String? = null
    @SerializedName("processName")
    var processName:String? =null
}