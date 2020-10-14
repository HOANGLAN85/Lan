package com.example.dhlan.Model.Loadcomment

import com.google.gson.annotations.SerializedName

class Loadmodelbody {
    @SerializedName("companyId")
    var companyId: Int? = null
    @SerializedName("type")
    var type: Int? = null
    @SerializedName("pageSize")
    var pageSize: Int? = null
    @SerializedName("pageIndex")
    var pageIndex: Int? = null
    @SerializedName("isPaging")
    var isPaging: Int? = null

    constructor(companyId: Int?, type: Int?, pageSize: Int?, pageIndex: Int?, isPaging: Int?) {
        this.companyId = companyId
        this.type = type
        this.pageSize = pageSize
        this.pageIndex = pageIndex
        this.isPaging = isPaging
    }
}